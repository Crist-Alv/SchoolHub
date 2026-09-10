package com.schoolhub.repository;

import com.schoolhub.entity.Persona;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonaRepository extends ListCrudRepository<Persona, Long> {

}
