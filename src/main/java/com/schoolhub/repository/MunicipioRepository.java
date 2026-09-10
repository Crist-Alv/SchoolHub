package com.schoolhub.repository;

import com.schoolhub.entity.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

    List<Municipio> findByDepartamento_Id(Long id);

}
