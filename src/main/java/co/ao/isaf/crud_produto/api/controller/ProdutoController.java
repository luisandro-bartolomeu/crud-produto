package co.ao.isaf.crud_produto.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ao.isaf.crud_produto.api.service.ProdutoService;
import co.ao.isaf.crud_produto.domain.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/produtos")
@Tag(name = "Produtos", description = "Operações de Gestão de Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Listar todos os produtos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produtos Listados"),
        @ApiResponse(responseCode = "404", description = "Produtos Não Encontrado") 
    })
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        logger.info("[LISTAR] Solicitado lista de produtos");
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    // public List<Produto> listarProdutos(){
    // return produtoService.listarProdutos();
    // }

    @Operation(summary = "Criar Produto")
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        logger.info("[CRIAR] Novo produto: {}", produto.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criaProduto(produto));
    }
   

    @Operation(summary = "Buscar Produto pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPeloId(@Valid @PathVariable Long id) {
        logger.info("[BUSCAR] Produto com ID: {}", id);
        var p = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(p.isPresent()? p.get() : null);
    }
   

    @Operation(summary = "Actualizar Produto Pelo ID")
    @ApiResponse(responseCode = "200", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> actualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto novoProduto) {
        logger.info("[ATUALIZAR] Produto ID: {} com dados: {}", id, novoProduto.getNome());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.actualizarProduto(id, novoProduto));
    }
    /*
     * @PutMapping("/{id}")
     * public Produto actualizarProduto(@PathVariable Long id, @RequestBody Produto
     * novoProduto){
     * return produtoService.actualizarProduto(id, novoProduto);
     * }
     */

    @Operation(summary = "Eliminar Produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
        logger.info("[DELETAR] Produto ID: {}", id);
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * public void removerProduto(@PathVariable Long id){
     * produtoService.removerProduto(id);
     * }
     */

}
