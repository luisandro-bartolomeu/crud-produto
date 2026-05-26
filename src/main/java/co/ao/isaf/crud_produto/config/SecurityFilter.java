package co.ao.isaf.crud_produto.config;

import co.ao.isaf.crud_produto.api.repository.UserRepository;
import co.ao.isaf.crud_produto.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null){
            var username = tokenService.validateToken(token);
            UserDetails user = userRepository.findByUsername(username);

            // CORREÇÃO: Garante que o código só avança se o utilizador existir na BD
            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication); //guarda no contexto
            } else {
                // Caso o token seja válido mas o utilizador tenha sido apagado da BD
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // Interrompe a requisição aqui
            }
        }

        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        // CORREÇÃO: Se não começar com "Bearer ", ignora! Significa que pode ser um Basic Auth
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }

}
