package com.schoolhub.repository;

import com.schoolhub.entity.Docente;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DocenteRepository extends ListCrudRepository<Docente, Long> {

    List<Docente> findByActivo(Boolean activo);
}
