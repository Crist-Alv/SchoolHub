package app.escuela.web;

import app.escuela.service.DocenteService;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class EscuelaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {

        return "index";
    }

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/docente")
    public String docente(Model model) {

        var docentes = docenteService.listarDocentes();

        model.addAttribute("docentes", docentes);
        return "docs/docente";
    }
}
