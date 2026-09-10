package com.schoolhub.service;

import com.schoolhub.entity.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionService {

    List<Direccion> listarDirecciones();

    void guardar(Direccion direccion);

    Optional<Direccion> obtenerPersona_Id(Long personaId);

    Direccion obtenerDireccion(Long id);
}
