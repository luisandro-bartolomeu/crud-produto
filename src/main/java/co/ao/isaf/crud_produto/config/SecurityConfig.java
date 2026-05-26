package co.ao.isaf.crud_produto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.ao.isaf.crud_produto.domain.enums.Perfil;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable()) //Desactivar o csrf;
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults()).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "api/v1/produtos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/utilizadores").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .anyRequest().permitAll())
                //authorizeHttpRequests determinamos quais são os endpoints que queremos que tenha autorização
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
                //.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                /* seu SecurityFilter (o que valida o JWT) é colocado antes na cadeia de filtros porque ele serve para as outras requisições
                (como GET /produtos).Se a requisição for para o /login: O seu SecurityFilter deixa passar (porque não há JWT ainda)
                e a autenticação é tratada pelo Controller ou pelo filtro de login customizado.Se a requisição for para outra rota protegida:
                 O seu SecurityFilter interceta, valida o JWT e injeta o utilizador no contexto, impedindo que qualquer mecanismo de login por palavra-passe seja disparado.
                 */
        return httpSecurity.build(); //Construir o a camada de segurança ou filtro de segurança com as configuranças feitas em cima

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
