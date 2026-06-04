package co.ao.isaf.crud_produto.api.repository;

import co.ao.isaf.crud_produto.domain.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Pesquisa por nome (índice idx_produto_nome)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Pesquisa por categoria
    List<Produto> findByCategoriaId(Long categoriaId);

    // Pesquisa por preço (índice idx_produto_preco)
    List<Produto> findByPrecoBetween(Double min, Double max);

    // Pesquisa por stock (índice idx_produto_stock)
    List<Produto> findByStockLessThan(Integer stock);

    // Pesquisa combinada (índice idx_produto_nome_preco)
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome% AND p.preco <= :maxPreco")
    List<Produto> searchByNomeAndMaxPreco(@Param("nome") String nome, @Param("maxPreco") Double maxPreco);

    // Pesquisa avançada com paginação
    @Query("SELECT p FROM Produto p WHERE " +
            "(:nome IS NULL OR p.nome LIKE %:nome%) AND " +
            "(:categoriaId IS NULL OR p.categoria.id = :categoriaId) AND " +
            "(:minPreco IS NULL OR p.preco >= :minPreco) AND " +
            "(:maxPreco IS NULL OR p.preco <= :maxPreco)")
    Page<Produto> searchProdutos(@Param("nome") String nome,
                                 @Param("categoriaId") Long categoriaId,
                                 @Param("minPreco") Double minPreco,
                                 @Param("maxPreco") Double maxPreco,
                                 Pageable pageable);
}