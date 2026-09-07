package com.schoolhub.service;

import com.schoolhub.entity.Especialidad;

import java.util.List;

public interface EspecialidadService {

    List<Especialidad> listarEspecialidades();

    void guardar(Especialidad especialidad);

    Especialidad obtenerEspecialidad(Long id);
}
