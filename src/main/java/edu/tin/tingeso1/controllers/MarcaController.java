package edu.tin.tingeso1.controllers;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.tin.tingeso1.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping
public class MarcaController {
    @Autowired
    MarcaService marcaService;

    @PostMapping("/loaddata")
    public String loaddata(RedirectAttributes ms) throws IOException, ParseException {
        marcaService.leerData();
        ms.addFlashAttribute("mensaje","se importo con exito");
        return "redirect:/importar";
    }

    @GetMapping("/importar")
    public String importar() {
        return "importar";
    }
}
