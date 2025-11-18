package com.prueba.mantenedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.mantenedor.dto.UsuarioDto;
import com.prueba.mantenedor.request.UsuarioLoginRequest;
import com.prueba.mantenedor.service.UsuarioService;
import com.prueba.mantenedor.web.response.ApiResponse;

import jakarta.validation.Valid;

import java.util.Locale;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<UsuarioDto>> login(@Valid @RequestBody UsuarioLoginRequest usuarioRequest,
            Locale locale) {
        return usuarioService.login(usuarioRequest, locale);
    }

}
