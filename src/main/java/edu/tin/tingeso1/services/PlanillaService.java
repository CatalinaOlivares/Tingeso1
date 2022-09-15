package edu.tin.tingeso1.services;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.entities.PlanillaEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import edu.tin.tingeso1.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
//capa de logica de negocios
@Service
public class PlanillaService {
    @Autowired
    EmpleadoRepository planillaRepository;
    public ArrayList<EmpleadoEntity> obtenerPlanilla(){
        return (ArrayList<EmpleadoEntity>) planillaRepository.findAll();
    }
}
