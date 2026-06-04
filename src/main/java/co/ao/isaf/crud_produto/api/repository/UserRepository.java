package co.ao.isaf.crud_produto.api.repository;

import co.ao.isaf.crud_produto.domain.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Utilizador, Long> {
    public UserDetails findByUsername(String username);

    boolean existsByUsername(String usernameAdmin);
}