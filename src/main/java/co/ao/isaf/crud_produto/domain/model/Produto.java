package co.ao.isaf.crud_produto.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto",
        indexes = {
                @Index(name = "idx_produto_nome", columnList = "nome"),
                @Index(name = "idx_produto_preco", columnList = "preco"),
                @Index(name = "idx_produto_categoria", columnList = "categoria_id"),
                @Index(name = "idx_produto_stock", columnList = "stock"),
                @Index(name = "idx_produto_nome_preco", columnList = "nome, preco")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<EncomendaItem> encomendaItens = new ArrayList<>();
}

