package co.ao.isaf.crud_produto.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.ao.isaf.crud_produto.api.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.ao.isaf.crud_produto.domain.model.Produto;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    public ProdutoService(ProdutoRepository produtoRepository){
        logger.info("[INICIALIZAR] Carregando produtos padrão");
        this.produtoRepository = produtoRepository;

    }

    public Produto criaProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    //Muito perigoso, adicionar paginação depois - Débito técnico
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Long id){
        logger.debug("[BUSCAR] Procurando produto com ID: {}", id);
        var p = produtoRepository.findById(id);
        logger.warn("[BUSCAR] Produto não encontrado para ID: {}", id);
        return p;
    }

    public Produto actualizarProduto(Long id, Produto novoProduto){
        logger.debug("[ATUALIZAR] Atualizando produto ID: {}", id);
        //adicionar validações;
        var p = produtoRepository.findById(id);
        Produto existeProduto = produtoRepository.save(p.get());
        logger.info("[ATUALIZAR] Produto {} atualizado com sucesso", id);
        return existeProduto;
    }

    public void removerProduto(Long id){
        logger.debug("[DELETAR] Removendo produto ID: {}", id);
        Optional<Produto> existeProduto = produtoRepository.findById(id);
        if(existeProduto.isPresent()){
            produtoRepository.delete(existeProduto.get());
        }
    }

}
