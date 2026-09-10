package com.schoolhub.service.impl;

import com.schoolhub.entity.Direccion;
import com.schoolhub.entity.Persona;
import com.schoolhub.repository.DireccionRepository;
import com.schoolhub.repository.DocenteRepository;
import com.schoolhub.entity.Docente;
import com.schoolhub.repository.PersonaRepository;
import com.schoolhub.service.DocenteService;
import org.springframework.transaction.annotation.Transactional;
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

        // Verificar si estamos editando un docente existente
        if (docente.getId() != null) {

            Docente docenteExistente = docenteRepository
                    .findById(docente.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Docente no encontrado"));

            // No permitir editar docentes inactivos
            if (!docenteExistente.getActivo()) {
                throw new IllegalStateException(
                        "No se puede editar un docente inactivo");
            }

            // Conservar datos que no vienen del formulario
            docente.setFechaCreacion(docenteExistente.getFechaCreacion());
            docente.setActivo(docenteExistente.getActivo());
        }

        // Guardar o actualizar la persona
        Persona persona = docente.getPersona();

        personaRepository.save(persona);

        // Si la dirección ya existe, actualizarla
        if (direccion.getId() != null) {

            Direccion direccionExistente = direccionRepository
                    .findById(direccion.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Dirección no encontrada"));

            direccionExistente.setDepartamento(direccion.getDepartamento());
            direccionExistente.setMunicipio(direccion.getMunicipio());
            direccionExistente.setZona(direccion.getZona());
            direccionExistente.setTipoVia(direccion.getTipoVia());
            direccionExistente.setDireccion(direccion.getDireccion());
            direccionExistente.setReferencia(direccion.getReferencia());
            direccionExistente.setLugar_nacimiento(
                    direccion.getLugar_nacimiento()
            );

            direccionRepository.save(direccionExistente);

        } else {

            // Crear una nueva dirección
            direccion.setPersona(persona);

            direccionRepository.save(direccion);
        }

        // Guardar o actualizar el docente
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
