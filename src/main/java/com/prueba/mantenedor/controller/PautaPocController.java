package com.prueba.mantenedor.controller;

import org.springframework.web.bind.annotation.*;

import com.prueba.mantenedor.model.PautaPoc;
import com.prueba.mantenedor.service.PautaPocService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pautas")
public class PautaPocController {

    private final PautaPocService service;

    public PautaPocController(PautaPocService service) {
        this.service = service;
    }

    // Buscar por NUM_POC
    @GetMapping("/{numPoc}")
    public Optional<PautaPoc> buscar(@PathVariable Integer numPoc) {
        return service.buscarPorNumPoc(numPoc);
    }

    // Obtener todo
    @GetMapping
    public List<PautaPoc> getAll() {
        return service.getAll();
    }
}