package com.prueba.mantenedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl") // ESFC: Habilitar auditoría JPA
@SpringBootApplication
public class MantenedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantenedorApplication.class, args);
	}

}
