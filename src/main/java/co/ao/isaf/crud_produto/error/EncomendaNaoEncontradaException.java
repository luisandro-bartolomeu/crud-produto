package co.ao.isaf.crud_produto.error;

public class EncomendaNaoEncontradaException extends RuntimeException {
    public EncomendaNaoEncontradaException(Long id) {
        super("Encomenda não encontrada para o ID: " + id);
    }
}
