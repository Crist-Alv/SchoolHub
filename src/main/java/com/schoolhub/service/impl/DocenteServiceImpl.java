package com.schoolhub.service.impl;

import com.schoolhub.entity.Direccion;
import com.schoolhub.entity.Persona;
import com.schoolhub.repository.DireccionRepository;
import com.schoolhub.repository.DocenteRepository;
import com.schoolhub.entity.Docente;
import com.schoolhub.repository.PersonaRepository;
import com.schoolhub.service.DocenteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;
    private final PersonaRepository personaRepository;
    private final DireccionRepository direccionRepository;

    public DocenteServiceImpl(
            DocenteRepository docenteRepository,
            PersonaRepository personaRepository,
            DireccionRepository direccionRepository) {

        this.docenteRepository = docenteRepository;
        this.personaRepository = personaRepository;
        this.direccionRepository = direccionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Docente> listarDocentes() {
        return docenteRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Docente docente, Direccion direccion) {

        if (docente.getId() != null) {
            Docente existente = docenteRepository.findById(docente.getId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

            docente.setActivo(existente.getActivo());
            docente.setFechaCreacion(existente.getFechaCreacion());
        }

        Persona persona = docente.getPersona();

        personaRepository.save(persona);

        direccion.setPersona(persona);

        direccionRepository.save(direccion);

        docenteRepository.save(docente);
    }

    @Override
    @Transactional(readOnly = true)
    public Docente obtenerDocente(Long id) {
        return docenteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> obtenerDireccionPorPersona(Long personaId) {
        return direccionRepository.findByPersona_Id(personaId);
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id) {

        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        docente.setActivo(!docente.getActivo());

        docenteRepository.save(docente);
    }
}
