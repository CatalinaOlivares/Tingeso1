package edu.tin.tingeso1.controllers;
import edu.tin.tingeso1.services.OficinaRRH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping
public class PlanillaController {
    @Autowired
    OficinaRRH oficinaRRH;
    @RequestMapping(value="/loadPlanilla", method = {RequestMethod.GET, RequestMethod.PUT})
    public String loadPlanilla(RedirectAttributes ms){
        oficinaRRH.update();
        ms.addFlashAttribute("mensaje","Se ha calculado la planilla");
        return "redirect:/calcular_planilla";
    }
    @GetMapping("/calcular_planilla")
    public String calcular() {
        return "calcular_planilla";
    }

}
