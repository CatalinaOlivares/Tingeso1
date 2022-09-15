package edu.tin.tingeso1.services;

import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
//capa de logica de negocios
@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();}

    public EmpleadoEntity guardarEmpleado(EmpleadoEntity empleado){
        return empleadoRepository.save(empleado);
    }

    public Optional<EmpleadoEntity> obtenerPorId(Long id){
        return empleadoRepository.findById(id);
    }

    //public EmpleadoEntity obtenerPorRut(String rut){return empleadoRepository.findByRut(rut);}

    public boolean eliminarEmpleado(Long id) {
        try{
            empleadoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
