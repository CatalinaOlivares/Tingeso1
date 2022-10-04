package edu.tin.tingeso1.controllers;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.services.EmpleadoService;
import edu.tin.tingeso1.services.OficinaRRH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;


@Controller
@RequestMapping
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    OficinaRRH oficinaRRH;

    @GetMapping("/")
    public String listar(Model model) {
        ArrayList<EmpleadoEntity>empleados=empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        return "index";
    }
    @GetMapping("/ingresar_Justificativos")
        public String ingresarJustificativo(){
        return "ingresar_Justificativos";
    }

    @RequestMapping(value="load_ingresar_Justificativos", method = {RequestMethod.GET, RequestMethod.PUT} )
        public String ingresarJustificativo(@RequestParam String rut, @RequestParam String fecha, RedirectAttributes ms ) throws ParseException {
        int resultado = oficinaRRH.ingresarJustificativo(rut,fecha);
        switch(resultado){
            case -2 -> ms.addFlashAttribute("mensaje","Dia ya justificado");
            case -1 -> ms.addFlashAttribute("mensaje","Datos Erroneos, Marca no existe");
            case 1 -> ms.addFlashAttribute("mensaje","se ingresó el justificativo con exito");
            case 0 -> ms.addFlashAttribute("mensaje","No puede optar a justificativo");
        }
        // usar el metodo para cargar justificativo
        return "redirect:/ingresar_Justificativos";
    }


    @GetMapping("/ingresar_autorizaciones_horas_extras")
    public String ingresarAutorizacionHorEx(){
        return "ingresar_autorizaciones_horas_extras";
    }

    @RequestMapping(value="/load_ingresar_autorizaciones_horas_extras", method = {RequestMethod.GET, RequestMethod.PUT})
    public String ingresarAutorizacionHorEx(@RequestParam String rut , @RequestParam Date fecha, RedirectAttributes ms){
        Integer resultado=oficinaRRH.ingresarJusHorExtra(rut,fecha);
        switch(resultado){
            case 0 -> ms.addFlashAttribute("mensaje","Se autorizaron las horas extras");
            case 1 -> ms.addFlashAttribute("mensaje","Datos Erroneos, Marca no existe");
        }
        return "redirect:/ingresar_autorizaciones_horas_extras";
    }


    @GetMapping("/mostrar_reporte")
    public String mostrarReporte(Model model) {
        ArrayList<EmpleadoEntity>empleados=empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        return "mostrar_reporte";
    }




}