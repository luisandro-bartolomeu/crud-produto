package co.ao.isaf.crud_produto.domain.dto;

import co.ao.isaf.crud_produto.domain.enums.Estado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EncomendaRequest(
        @NotNull Long usuarioId,
        Estado estado,
        @NotEmpty @Valid List<EncomendaItemRequest> itens
) {
}
