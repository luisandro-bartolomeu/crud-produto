package co.ao.isaf.crud_produto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API de Produtos")
                        .version("1.0.0")
                        .description("API REST para gestão de produtos - ISAF")
                        .contact(new Contact()
                                .name("INSTITUTO SUPERIOR DE ADMINSTRAÇÃO E FINANÇAS / ISAF")
                                .email("geral@isaf.co.ao")))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .description("HTTP Basic Authentication")));
    }
}
