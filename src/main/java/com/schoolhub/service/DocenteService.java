package com.schoolhub.service;

import com.schoolhub.entity.Docente;

import java.util.List;

public interface DocenteService {

    public List<Docente> listarDocentes();

    public void guardar(Docente docente);

    public Docente obtenerDocente(Docente docente);
}
