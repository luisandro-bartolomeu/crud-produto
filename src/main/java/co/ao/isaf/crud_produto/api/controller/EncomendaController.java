package co.ao.isaf.crud_produto.api.controller;

import co.ao.isaf.crud_produto.api.service.EncomendaService;
import co.ao.isaf.crud_produto.domain.dto.EncomendaRequest;
import co.ao.isaf.crud_produto.domain.model.Encomenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/encomendas")
@Tag(name = "Encomendas", description = "Operações de gestão de encomendas")
public class EncomendaController {

    private final EncomendaService encomendaService;
    private static final Logger logger = LoggerFactory.getLogger(EncomendaController.class);

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @Operation(summary = "Listar todas as encomendas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Encomendas listadas")
    })
    @GetMapping
    public ResponseEntity<List<Encomenda>> listarEncomendas() {
        logger.info("[LISTAR] Solicitada lista de encomendas");
        return ResponseEntity.ok(encomendaService.listarEncomendas());
    }

    @Operation(summary = "Buscar encomenda pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Encomenda> buscarEncomendaPorId(@PathVariable Long id) {
        logger.info("[BUSCAR] Encomenda com ID: {}", id);
        return ResponseEntity.ok(encomendaService.buscarPorId(id));
    }

    @Operation(summary = "Criar encomenda")
    @PostMapping
    public ResponseEntity<Encomenda> criarEncomenda(@Valid @RequestBody EncomendaRequest request) {
        logger.info("[CRIAR] Nova encomenda para usuario ID: {}", request.usuarioId());
        return ResponseEntity.status(HttpStatus.CREATED).body(encomendaService.criarEncomenda(request));
    }

    @Operation(summary = "Actualizar encomenda")
    @PutMapping("/{id}")
    public ResponseEntity<Encomenda> actualizarEncomenda(@PathVariable Long id,
                                                         @Valid @RequestBody EncomendaRequest request) {
        logger.info("[ATUALIZAR] Encomenda ID: {}", id);
        return ResponseEntity.ok(encomendaService.actualizarEncomenda(id, request));
    }

    @Operation(summary = "Eliminar encomenda")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEncomenda(@PathVariable Long id) {
        logger.info("[DELETAR] Encomenda ID: {}", id);
        encomendaService.removerEncomenda(id);
        return ResponseEntity.noContent().build();
    }
}
