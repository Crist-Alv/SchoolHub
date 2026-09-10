package com.schoolhub.repository;

import com.schoolhub.entity.Direccion;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface DireccionRepository extends ListCrudRepository<Direccion, Long> {

    Optional<Direccion> findByPersona_Id(Long personaId);
}
