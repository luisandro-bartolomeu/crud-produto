package co.ao.isaf.crud_produto.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "encomenda_item",
        indexes = {
                @Index(name = "idx_encomenda_item_encomenda", columnList = "encomenda_id"),
                @Index(name = "idx_encomenda_item_produto", columnList = "produto_id"),
                @Index(name = "idx_encomenda_item_composite", columnList = "encomenda_id, produto_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncomendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encomenda_id", nullable = false)
    @JsonIgnore
    private Encomenda encomenda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private Double precoUnitario;
}