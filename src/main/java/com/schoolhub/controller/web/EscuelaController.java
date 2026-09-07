package com.schoolhub.controller.web;

import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class EscuelaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {

        model.addAttribute("active", "");
        model.addAttribute("helpFile", "InicioP.pdf");
        return "index";
    }
}
