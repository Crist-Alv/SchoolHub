package com.schoolhub.service;

import com.schoolhub.entity.Municipio;

import java.util.List;

public interface MunicipioService {

    public List<Municipio> listarPorDepartamento(Integer iddep);

}