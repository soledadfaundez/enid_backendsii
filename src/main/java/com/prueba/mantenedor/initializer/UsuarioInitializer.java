package com.prueba.mantenedor.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prueba.mantenedor.request.UsuarioRequest;
import com.prueba.mantenedor.service.UsuarioService;

import java.util.Locale;

//SFC: Crear los usuarios admin al inicio 
@Component
public class UsuarioInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioConfig usuarioConfig;

    // Inyectar la password por default para pruebas
    @Value("${password.default}")
    private String passDefault;

    // Inyectar el mail por default para pruebas
    @Value("${mail.default}")
    private String mailDefault;

    @Override
    public void run(String... args) {
        usuarioConfig.getData().forEach((alias, name) -> {
            // Crear UserRequest con datos básicos
            System.out.println("Creating user: " + name + " with alias: " + alias);

            UsuarioRequest userRequest = new UsuarioRequest();
            userRequest.setNombre(name);
            userRequest.setEmail(mailDefault);
            userRequest.setPassword(passDefault); // Contraseña por defecto
            // Locale fijo en español
            Locale locale = new Locale("es");
            usuarioService.save(userRequest, locale);
        });
    }

}

/*
 * SFC: La clase implementa CommandLineRunner, lo que significa que su método
 * run se ejecutará al inicio de la aplicación.
 * Dentro del método run, itera sobre los datos de configuración
 * (userConfig.getData())
 * y, para cada par de alias y nombre, crea un
 * nuevo User con dos números de teléfono (Phone) asociados, luego guarda ese
 * usuario
 * utilizando el servicio userService.
 */