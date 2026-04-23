package app.escuela.service;

import app.escuela.domain.Docente;

import java.util.List;

public interface DocenteService {

    public List<Docente> listarDocentes();

    public void guardar(Docente docente);

    public void eliminar(Docente docente);

    public Docente obtenerDocente(Docente docente);
}
