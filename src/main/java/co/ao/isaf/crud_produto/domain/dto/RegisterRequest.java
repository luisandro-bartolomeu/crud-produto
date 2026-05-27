package co.ao.isaf.crud_produto.domain.dto;

import co.ao.isaf.crud_produto.domain.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record RegisterRequest(
        @NotBlank String username,
        @Email @NotBlank String name,
        @NotBlank String password,
        Perfil perfil
) {
}