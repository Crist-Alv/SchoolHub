package com.schoolhub.service.impl;

import com.schoolhub.repository.DocenteRepository;
import com.schoolhub.entity.Docente;
import com.schoolhub.service.DocenteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Docente> listarDocentes() {
        return docenteRepository.findByEstado(1);
    }

    @Override
    @Transactional
    public void guardar(Docente docente) {
        docenteRepository.save(docente);
    }

    @Override
    @Transactional(readOnly = true)
    public Docente obtenerDocente(Docente docente) {
        return docenteRepository.findById(docente.getIddocente()).orElse(null);
    }
}
