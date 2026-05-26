package co.ao.isaf.crud_produto.domain.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}