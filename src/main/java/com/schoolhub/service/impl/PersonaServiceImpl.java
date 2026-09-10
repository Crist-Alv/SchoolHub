package com.schoolhub.service.impl;

import com.schoolhub.entity.Persona;
import com.schoolhub.repository.PersonaRepository;
import com.schoolhub.service.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona obtenerPersona(Long id) {
        return personaRepository.findById(id).orElse(null);
    }
}
