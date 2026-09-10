package com.schoolhub.service.impl;

import com.schoolhub.entity.Especialidad;
import com.schoolhub.repository.EspecialidadRepository;
import com.schoolhub.service.EspecialidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Especialidad especialidad) {
        especialidadRepository.save(especialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Especialidad obtenerEspecialidad(Long id) {
        return especialidadRepository.findById(id).orElse(null);
    }
}
