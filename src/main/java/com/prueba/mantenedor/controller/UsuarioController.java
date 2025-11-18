package com.prueba.mantenedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.mantenedor.dto.UsuarioDto;

import com.prueba.mantenedor.request.UsuarioRequest;
import com.prueba.mantenedor.service.UsuarioService;
import com.prueba.mantenedor.web.response.ApiResponse;

import jakarta.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("")
    public ResponseEntity<ApiResponse<UsuarioDto>> save(@Valid @RequestBody UsuarioRequest usuarioRequest,
            Locale locale) {
        return usuarioService.save(usuarioRequest, locale);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UsuarioDto>>> findAll(Locale locale) {
        return usuarioService.findAll(locale);
    }

}
