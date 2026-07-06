package com.schoolhub.service.impl;

import com.schoolhub.repository.MunicipioRepository;
import com.schoolhub.entity.Municipio;
import com.schoolhub.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public List<Municipio> listarPorDepartamento(Integer iddep) {
        return municipioRepository.findByDepartamento_Iddep(iddep);
    }
}