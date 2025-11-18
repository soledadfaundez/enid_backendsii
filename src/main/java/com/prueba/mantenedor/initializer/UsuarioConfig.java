package com.prueba.mantenedor.initializer;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "usuario")
public class UsuarioConfig {

    private Map<String, String> data;

}
