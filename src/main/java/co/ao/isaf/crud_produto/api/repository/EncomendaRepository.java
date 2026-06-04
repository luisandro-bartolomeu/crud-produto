package co.ao.isaf.crud_produto.api.repository;

import co.ao.isaf.crud_produto.domain.enums.Estado;
import co.ao.isaf.crud_produto.domain.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    // Pesquisa por usuário (índice idx_encomenda_usuario)
    List<Encomenda> findByUsuarioId(Long usuarioId);

    // Pesquisa por estado (índice idx_encomenda_estado)
    List<Encomenda> findByEstado(Estado estado);

    // Pesquisa por período (índice idx_encomenda_data)
    List<Encomenda> findByDataCriacaoBetween(LocalDateTime start, LocalDateTime end);

    // Pesquisa combinada usuário e estado (índice idx_encomenda_usuario_estado)
    List<Encomenda> findByUsuarioIdAndEstado(Long usuarioId, Estado estado);

    // Pesquisa combinada data e estado (índice idx_encomenda_data_estado)
    List<Encomenda> findByDataCriacaoBetweenAndEstado(LocalDateTime start, LocalDateTime end, Estado estado);

    // Encontra encomendas com total acima de um valor
    @Query("SELECT e FROM Encomenda e WHERE e.total > :valor")
    List<Encomenda> findEncomendasByTotalGreaterThan(@Param("valor") Double valor);
}