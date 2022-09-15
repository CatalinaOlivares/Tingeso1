package edu.tin.tingeso1.controllers;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.entities.PlanillaEntity;
import edu.tin.tingeso1.services.MarcaService;
import edu.tin.tingeso1.services.OficinaRRH;
import edu.tin.tingeso1.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
@Controller
@RequestMapping
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    @Autowired
    OficinaRRH oficinaRRH;
    @RequestMapping(value="/loadPlanilla", method = {RequestMethod.GET, RequestMethod.PUT})
    public String loadPlanilla(RedirectAttributes ms) throws IOException, ParseException {
        oficinaRRH.Update();
        ms.addFlashAttribute("mensaje","se importo con exito");
        return "redirect:/calcular_planilla";
    }

    @GetMapping("/calcular_planilla")
    public String calcular() {
        return "calcular_planilla";
    }

}
