package com.prueba.mantenedor.service;

import java.util.List;
import java.util.Optional;

import com.prueba.mantenedor.model.PautaPoc;

public interface PautaPocService {
    List<PautaPoc> getAll();

    Optional<PautaPoc> buscarPorNumPoc(Integer numPoc);
}
