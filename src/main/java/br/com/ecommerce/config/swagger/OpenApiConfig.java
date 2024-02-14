package br.com.ecommerce.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI();
    }
}
