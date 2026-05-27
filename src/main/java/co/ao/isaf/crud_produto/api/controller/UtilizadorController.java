package co.ao.isaf.crud_produto.api.controller;

import java.net.URI;
import java.util.List;

import co.ao.isaf.crud_produto.domain.dto.LoginRequest;
import co.ao.isaf.crud_produto.domain.dto.RegisterRequest;
import co.ao.isaf.crud_produto.domain.dto.RegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ao.isaf.crud_produto.api.service.UtilizadorService;
import co.ao.isaf.crud_produto.domain.model.Utilizador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/utilizadores")
@Tag(name = "Utilizadores", description = "Operações de Gestão dos Utilizadores do Sistema")
public class UtilizadorController {
    
    private final UtilizadorService utilizadorService;
    private static final Logger logger = LoggerFactory.getLogger(UtilizadorController.class);
    
    public UtilizadorController(UtilizadorService utilizadorService){
        this.utilizadorService = utilizadorService;
    }

    @Operation(summary = "Listar todos os utilizadores")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilizadores Listados"),
        @ApiResponse(responseCode = "404", description = "Utilizadores Não Encontrado") 
    })
    @GetMapping
    public ResponseEntity<List<RegisterResponse>> listarUtilizador() {
        logger.info("[LISTAR] Solicitado lista de utilizadores");
        return ResponseEntity.status(HttpStatus.OK).body(utilizadorService.listarUtilizadors());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest l){
        return ResponseEntity.ok( utilizadorService.Login(l));
    }

    @Operation(summary = "Criar Utilizador")
    @PostMapping("/register")
    public ResponseEntity<?> criarUtilizador(@Valid @RequestBody RegisterRequest utilizador){
        var utilizador2 = utilizadorService.criaUtilizador(utilizador);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("api/v1/utilizadores/"+utilizador2.Id())).body(utilizador2);
    }

}
