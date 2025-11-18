package com.prueba.mantenedor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {

                // Security Scheme para JWT
                SecurityScheme securityScheme = new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization");

                // Security Requirement
                SecurityRequirement securityRequirement = new SecurityRequirement()
                                .addList("bearerAuth");

                // Configuración final
                return new OpenAPI()
                                .info(new Info()
                                                .title("Test Apis")
                                                .description("Test Apis h2")
                                                .version("1.0")
                                                .contact(new Contact()
                                                                .name("Soledad Faundez")
                                                                .url("https://github.com/soledadfaundez")
                                                                .email("sole.faundez.c@gmail.com")))
                                .components(new Components()
                                                .addSecuritySchemes("bearerAuth", securityScheme))
                                .addSecurityItem(securityRequirement);
        }
}
