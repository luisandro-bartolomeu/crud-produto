package co.ao.isaf.crud_produto.config;

import co.ao.isaf.crud_produto.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.ao.isaf.crud_produto.api.service.UtilizadorService;
import co.ao.isaf.crud_produto.domain.model.Utilizador;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("[AUTENTICACAO] Tentativa de login do utilizador: {}", username);
        return userRepository.findByUsername(username);
    }
}
