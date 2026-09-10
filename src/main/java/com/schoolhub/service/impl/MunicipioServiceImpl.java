package com.schoolhub.service.impl;

import com.schoolhub.repository.MunicipioRepository;
import com.schoolhub.entity.Municipio;
import com.schoolhub.service.MunicipioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioServiceImpl(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public List<Municipio> listarPorDepartamento(Long id) {
        return municipioRepository.findByDepartamento_Id(id);
    }
}