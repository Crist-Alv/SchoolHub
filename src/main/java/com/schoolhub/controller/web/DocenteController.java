package com.schoolhub.controller.web;

import com.schoolhub.entity.*;
import com.schoolhub.service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DocenteController {

    private final DocenteService docenteService;
    private final DepartamentoService departamentoService;
    private final MunicipioService municipioService;
    private final EspecialidadService especialidadService;
    private final EstadoCivilService estadoCivilService;

    public DocenteController(
            DocenteService docenteService,
            DepartamentoService departamentoService,
            MunicipioService municipioService,
            EspecialidadService especialidadService,
            EstadoCivilService estadoCivilService) {

        this.docenteService = docenteService;
        this.departamentoService = departamentoService;
        this.municipioService = municipioService;
        this.especialidadService = especialidadService;
        this.estadoCivilService = estadoCivilService;
    }

    @GetMapping("/docente")
    public String docente(Model model) {

        var docentes = docenteService.listarDocentes();

        if (!model.containsAttribute("docente")) {

            Docente docente = new Docente();
            docente.setPersona(new Persona());

            model.addAttribute("docente", docente);
        }

        model.addAttribute("docentes", docentes);

        model.addAttribute("departamentos",departamentoService.listarDepartamentos());
        model.addAttribute("especialidades",especialidadService.listarEspecialidades());
        model.addAttribute("estadosCiviles", estadoCivilService.listarEstadoCivil());

        model.addAttribute("direccion", new Direccion());
        model.addAttribute("modoEditar", false);
        model.addAttribute("active", "docente");
        model.addAttribute("helpFile", "DocenteA.pdf");

        return "docs/docente/docente";
    }

    // METODO PARA EDITAR
    @GetMapping("/editar/{id}")
    public String editarDocente(@PathVariable Long id, Model model) {

        Docente docente = docenteService.obtenerDocente(id);

        // Si el docente no existe, regresar al listado
        if (docente == null) {
            return "redirect:/docente";
        }

        // Un docente inactivo no puede ser editado
        if (!docente.getActivo()) {
            return "redirect:/docente";
        }

        // Obtener la dirección asociada a la persona
        Direccion direccion = docenteService
                .obtenerDireccionPorPersona(docente.getPersona().getId())
                .orElse(new Direccion());

        model.addAttribute("docente", docente);
        model.addAttribute("direccion", direccion);

        model.addAttribute("docentes", docenteService.listarDocentes());
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());
        model.addAttribute("especialidades", especialidadService.listarEspecialidades());
        model.addAttribute("estadosCiviles", estadoCivilService.listarEstadoCivil());

        model.addAttribute("modoEditar", true);
        model.addAttribute("abrirModal", true);
        model.addAttribute("active", "docente");
        model.addAttribute("helpFile", "DocenteA.pdf");

        return "docs/docente/docente";
    }

    // CARGAR MUNICIPIOS POR AJAX
    @GetMapping("/municipios/{id}")
    @ResponseBody
    public List<Municipio> obtenerMunicipios(@PathVariable Long id) {
        return municipioService.listarPorDepartamento(id);
    }

    // METODO PARA GUARDAR
    @PostMapping("/docente/guardar")
    public String guardarDocente(
            @Valid @ModelAttribute("docente") Docente docente,
            @RequestParam("departamento.id") Long departamentoId,
            @RequestParam("municipio.id") Long municipioId,
            @RequestParam("direccionTexto") String direccionTexto) {

        Departamento departamento = departamentoService.listarDepartamentos().stream()
                .filter(item -> item.getId().equals(departamentoId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

        Municipio municipio = municipioService.listarPorDepartamento(departamentoId).stream()
                .filter(item -> item.getId().equals(municipioId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Municipio no corresponde al departamento"));

        Direccion direccion = docente.getId() == null
                ? new Direccion()
                : docenteService.obtenerDireccionPorPersona(docente.getPersona().getId())
                .orElseGet(Direccion::new);

        direccion.setDepartamento(departamento);
        direccion.setMunicipio(municipio);
        direccion.setDireccion(direccionTexto);

        docenteService.guardar(docente, direccion);

        return "redirect:/docente";
    }

    // METODO PARA CAMBIAR EL ESTADO (ACTIVO / INACTIVO)
    @PostMapping("/docente/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {

        docenteService.cambiarEstado(id);

        return "redirect:/docente";
    }
}
