package com.schoolhub.service.impl;

import com.schoolhub.repository.DepartamentoRepository;
import com.schoolhub.entity.Departamento;
import com.schoolhub.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }
}