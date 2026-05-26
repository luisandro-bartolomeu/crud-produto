package co.ao.isaf.crud_produto.api.repository;

import co.ao.isaf.crud_produto.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
