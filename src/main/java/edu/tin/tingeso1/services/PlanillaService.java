package edu.tin.tingeso1.services;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

//capa de logica de negocios
@Service
public class PlanillaService {
    @Autowired
    EmpleadoRepository planillaRepository;

}
