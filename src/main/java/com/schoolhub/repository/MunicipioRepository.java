package com.schoolhub.repository;

import com.schoolhub.entity.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {

    List<Municipio> findByDepartamento_Iddep(Integer iddep);

}
