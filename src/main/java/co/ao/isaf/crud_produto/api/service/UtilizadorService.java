package co.ao.isaf.crud_produto.api.service;

import java.util.ArrayList;
import java.util.List;

import co.ao.isaf.crud_produto.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.ao.isaf.crud_produto.domain.enums.Perfil;
import co.ao.isaf.crud_produto.domain.model.Utilizador;

@Service
public class UtilizadorService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UtilizadorService.class);
    private final TokenService tokenService;

    public UtilizadorService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService){
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String Login(Utilizador utilizador){
        var token = new UsernamePasswordAuthenticationToken(utilizador.getUsername(), utilizador.getPassword());
        /*O que o authenticationManager.authenticate faz?Chama o UserDetailsService: O Spring Security pega no username e
        vai à base de dados (usando o método loadUserByUsername) para carregar os dados reais do utilizador.
        Valida a Password: Ele utiliza o codificador de passwords configurado (ex: BCryptPasswordEncoder)
        para comparar a password recebida com o hash guardado na base de dados.Verifica o Estado da Conta:
        Valida se a conta não está bloqueada, expirada ou desativada.Retorna um Objeto Autenticado: Se tudo estiver correto,
        ele retorna um novo objeto Authentication, mas agora preenchido com as permissões
        (Authorities) do utilizador e com o estado de autenticado definido como true.Lança uma Exceção se Falhar:
        Se a password estiver errada ou o utilizador não existir, o método lança uma exceção automaticamente
         (ex: BadCredentialsException).*/
        Authentication authenticate = authenticationManager.authenticate(token);
        return tokenService.generateToken((Utilizador)authenticate.getPrincipal());
    }

    public Utilizador criaUtilizador(Utilizador utilizador){

        utilizador.setPassword(passwordEncoder.encode(utilizador.getPassword()));
        userRepository.save(utilizador);
        return utilizador;  
    }

    public List<Utilizador> listarUtilizadors (){
        return userRepository.findAll();
    } 

}
