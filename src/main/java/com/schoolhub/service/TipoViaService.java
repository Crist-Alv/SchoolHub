package com.schoolhub.service;

import com.schoolhub.entity.TipoVia;

import java.util.List;

public interface TipoViaService {

    List<TipoVia> listarTipoVia();

    void guardar(TipoVia tipoVia);

    TipoVia obtenerTipoVia(Long id);
}
