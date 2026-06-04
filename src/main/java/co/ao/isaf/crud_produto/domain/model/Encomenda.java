package co.ao.isaf.crud_produto.domain.model;
import co.ao.isaf.crud_produto.domain.enums.Estado;
import co.ao.isaf.crud_produto.domain.model.Utilizador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encomenda",
        indexes = {
                @Index(name = "idx_encomenda_usuario", columnList = "usuario_id"),
                @Index(name = "idx_encomenda_estado", columnList = "estado"),
                @Index(name = "idx_encomenda_data", columnList = "data_criacao"),
                @Index(name = "idx_encomenda_usuario_estado", columnList = "usuario_id, estado"),
                @Index(name = "idx_encomenda_data_estado", columnList = "data_criacao, estado")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Utilizador usuario;

    @OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<EncomendaItem> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Double total;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        if (estado == null) {
            estado = Estado.PENDENTE;
        }
    }
}
