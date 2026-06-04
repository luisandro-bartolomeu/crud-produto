package co.ao.isaf.crud_produto.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EncomendaItemRequest(
        @NotNull Long produtoId,
        @NotNull @Min(1) Integer quantidade,
        Double precoUnitario
) {
}
