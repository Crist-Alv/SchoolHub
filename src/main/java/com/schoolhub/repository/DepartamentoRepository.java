package com.schoolhub.repository;

import com.schoolhub.entity.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {

    List<Departamento> findAll();

}