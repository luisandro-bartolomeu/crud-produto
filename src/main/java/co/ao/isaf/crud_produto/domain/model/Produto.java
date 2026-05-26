package co.ao.isaf.crud_produto.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;
    
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço não pode ser negativo")
    private Double preco;

    @Min(value = 0, message = "o stock não pode ser negativo")
    private Long qtdStock;


    public Produto(Long id, String nome, String descricao, Double preco, Long qtdStock) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdStock = qtdStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getQtdStock() {
        return qtdStock;
    }

    public void setQtdStock(Long qtdStock) {
        this.qtdStock = qtdStock;
    }
}
