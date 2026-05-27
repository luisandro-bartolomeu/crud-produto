package co.ao.isaf.crud_produto.domain.dto;

import co.ao.isaf.crud_produto.domain.enums.Perfil;

public record RegisterResponse(Long Id, String name, String username, Perfil perfil) {
}
