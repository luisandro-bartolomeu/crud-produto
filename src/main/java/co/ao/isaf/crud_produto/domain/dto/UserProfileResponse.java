package co.ao.isaf.crud_produto.domain.dto;


import co.ao.isaf.crud_produto.domain.enums.Perfil;

import java.util.List;

public record UserProfileResponse(
        String username,
        Perfil perfil,
        String token
) {
}
