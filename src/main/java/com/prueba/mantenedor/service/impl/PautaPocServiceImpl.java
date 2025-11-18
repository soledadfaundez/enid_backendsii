package com.prueba.mantenedor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.mantenedor.dao.PautaPocRepository;
import com.prueba.mantenedor.model.PautaPoc;
import com.prueba.mantenedor.service.PautaPocService;

@Service
public class PautaPocServiceImpl implements PautaPocService {

    @Autowired
    private PautaPocRepository repository;

    public List<PautaPoc> getAll() {
        return repository.findAll();
    }

    public Optional<PautaPoc> buscarPorNumPoc(Integer numPoc) {
        return repository
                .findAll()
                .stream()
                .filter(p -> p.getNumPoc().equals(numPoc))
                .findFirst();
    }
}
