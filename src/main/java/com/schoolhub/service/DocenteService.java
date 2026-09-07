package com.schoolhub.service;

import com.schoolhub.entity.Direccion;
import com.schoolhub.entity.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {

    List<Docente> listarDocentes();

    void guardar(Docente docente, Direccion direccion);

    Docente obtenerDocente(Long id);

    Optional<Direccion> obtenerDireccionPorPersona(Long personaId);

    void cambiarEstado(Long id);
}
