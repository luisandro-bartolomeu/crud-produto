package co.ao.isaf.crud_produto.error;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id){
        super("Produto com ID: " + id + " não encontrado");
    }

}
