package com.schoolhub.service;

import com.schoolhub.entity.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> listarPersonas();

    void guardar(Persona persona);

    Persona obtenerPersona(Long id);
}
