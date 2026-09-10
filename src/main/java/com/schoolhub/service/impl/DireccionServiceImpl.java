package com.schoolhub.service.impl;

import com.schoolhub.entity.Direccion;
import com.schoolhub.repository.DireccionRepository;
import com.schoolhub.service.DireccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Direccion> listarDirecciones() {
        return direccionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Direccion obtenerDireccion(Long id) {
        return direccionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> obtenerPersona_Id(Long personaId) {
        return direccionRepository.findByPersona_Id(personaId);
    }

    @Override
    @Transactional
    public void guardar(Direccion direccion) {
        direccionRepository.save(direccion);
    }
}
