package co.ao.isaf.crud_produto.error;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ProdutoNaoEncontradoException ex){
        logger.warn("[ERRO] Produto não encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro: ", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidation(MethodArgumentNotValidException ex){
        logger.warn("[ERRO] Validação falhou com {} erros", ex.getBindingResult().getErrorCount());
        Map<String,String> erros = new HashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            logger.debug("[ERRO] Campo '{}' - {}", fieldError.getField(), fieldError.getDefaultMessage());
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    } 
}
