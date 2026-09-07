package com.schoolhub.service;

import com.schoolhub.entity.EstadoCivil;

import java.util.List;

public interface EstadoCivilService {

    List<EstadoCivil> listarEstadoCivil();

    void guardar(EstadoCivil estadoCivil);

    EstadoCivil obtenerEstadoCivil(Long id);
}
