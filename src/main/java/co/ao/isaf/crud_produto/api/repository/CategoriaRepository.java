package co.ao.isaf.crud_produto.api.repository;
import co.ao.isaf.crud_produto.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

    @Query("SELECT c FROM Categoria c WHERE c.nome LIKE %:nome%")
    List<Categoria> findByNomeContaining(@Param("nome") String nome);
}