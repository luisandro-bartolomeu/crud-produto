package co.ao.isaf.crud_produto.config;



import co.ao.isaf.crud_produto.api.repository.UserRepository;
import co.ao.isaf.crud_produto.domain.enums.Perfil;
import co.ao.isaf.crud_produto.domain.model.Utilizador;
 // Ajuste para o seu pacote correto
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseBootstrap implements CommandLineRunner {

    private final UserRepository utilizadorRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependência via construtor
    public DatabaseBootstrap(UserRepository utilizadorRepository, PasswordEncoder passwordEncoder) {
        this.utilizadorRepository = utilizadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String usernameAdmin = "admin";

        // 1. Evita duplicidade se o banco já tiver o registo
        if (!utilizadorRepository.existsByUsername(usernameAdmin)) {

            var admin = new Utilizador();
            admin.setUsername(usernameAdmin);

            // 2. OBRIGATÓRIO: Criptografar a password para o AuthenticationManager conseguir validar
            String passwordConfigurada = passwordEncoder.encode("admin123");
            admin.setPassword(passwordConfigurada);


            admin.setPerfil(Perfil.ADMIN);

            utilizadorRepository.save(admin);
            System.out.println("2026-05-26 - [BOOTSTRAP] Utilizador 'admin' criado com sucesso.");
        } else {
            System.out.println("2026-05-26 - [BOOTSTRAP] Utilizador 'admin' já existe no banco.");
        }
    }
}
