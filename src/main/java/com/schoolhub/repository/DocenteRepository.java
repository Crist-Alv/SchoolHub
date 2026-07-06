package com.schoolhub.repository;

import com.schoolhub.entity.Docente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocenteRepository extends CrudRepository<Docente, Long> {
    List<Docente> findByEstado(int i);
}
