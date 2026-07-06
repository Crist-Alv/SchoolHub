package com.schoolhub.controller;

import com.schoolhub.entity.Docente;
import com.schoolhub.entity.Municipio;
import com.schoolhub.service.DepartamentoService;
import com.schoolhub.service.DocenteService;
import com.schoolhub.service.MunicipioService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class EscuelaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {

        model.addAttribute("active", "");
        model.addAttribute("helpFile", "InicioP.pdf");
        return "index";
    }

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/docente")
    public String docente(Model model) {

        var docentes = docenteService.listarDocentes();

        if (!model.containsAttribute("docente")) {
            model.addAttribute("docente", new Docente());
        }

        model.addAttribute("docentes", docentes);
        model.addAttribute("modoEditar", false);
        model.addAttribute("active", "docente");
        model.addAttribute("helpFile", "DocenteA.pdf");
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());

        return "docs/docente/docente";
    }
    // CARGAR MUNICIPIOS POR AJAX
    @GetMapping("/municipios/{iddep}")
    @ResponseBody
    public List<Municipio> obtenerMunicipios(@PathVariable Integer iddep) {
        return municipioService.listarPorDepartamento(iddep);
    }

    @PostMapping("/guardar")
    public String guardarDocente(@Valid Docente docente) {

        docenteService.guardar(docente);

        return "redirect:/docente";
    }

    // =====================================================
// EDITAR DOCENTE Y MOSTRAR MODAL
// =====================================================
    @GetMapping("/editar/{iddocente}")
    public String editarDocente(@PathVariable Long iddocente,
                                Model model) {

        Docente temp = new Docente();
        temp.setIddocente(iddocente);

        Docente docente = docenteService.obtenerDocente(temp);

        model.addAttribute("docente", docente);

        model.addAttribute("docentes", docenteService.listarDocentes());

        model.addAttribute("departamentos",
                departamentoService.listarDepartamentos());

        model.addAttribute("municipios",
                municipioService.listarPorDepartamento(
                        docente.getDepartamento().getIddep()
                ));

        model.addAttribute("modoEditar", true);
        model.addAttribute("abrirModal", true);

        return "docs/docente/docente";
    }

    @GetMapping("/baja/{iddocente}")
    public String eliminarDocente(@PathVariable("iddocente") Long iddocente) {

        Docente docente = new Docente();
        docente.setIddocente(iddocente);

        docente = docenteService.obtenerDocente(docente);

        docente.setEstado(0);

        docenteService.guardar(docente);

        return "redirect:/docente";
    }
}
