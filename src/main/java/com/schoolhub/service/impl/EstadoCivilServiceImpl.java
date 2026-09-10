package com.schoolhub.service.impl;

import com.schoolhub.entity.EstadoCivil;
import com.schoolhub.repository.EstadoCivilRepository;
import com.schoolhub.service.EstadoCivilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilServiceImpl(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoCivil> listarEstadoCivil() {
        return estadoCivilRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(EstadoCivil estadoCivil) {
        estadoCivilRepository.save(estadoCivil);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoCivil obtenerEstadoCivil(Long id) {
        return estadoCivilRepository.findById(id).orElse(null);
    }
}
