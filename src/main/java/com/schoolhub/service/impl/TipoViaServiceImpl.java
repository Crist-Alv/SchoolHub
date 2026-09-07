package com.schoolhub.service.impl;

import com.schoolhub.entity.TipoVia;
import com.schoolhub.repository.TipoViaRepository;
import com.schoolhub.service.TipoViaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoViaServiceImpl implements TipoViaService {

    private final TipoViaRepository tipoViaRepository;

    public TipoViaServiceImpl(TipoViaRepository tipoViaRepository) {
        this.tipoViaRepository = tipoViaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoVia> listarTipoVia() {
        return tipoViaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(TipoVia tipoVia) {
        tipoViaRepository.save(tipoVia);
    }

    @Override
    @Transactional
    public TipoVia obtenerTipoVia(Long id) {
        return tipoViaRepository.findById(id).orElse(null);
    }
}
