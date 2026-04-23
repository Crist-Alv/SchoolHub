package app.escuela.dao;

import app.escuela.domain.Docente;
import org.springframework.data.repository.CrudRepository;

public interface DocenteDao extends CrudRepository<Docente, Long> {
}
