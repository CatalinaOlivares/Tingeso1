package edu.tin.tingeso1.services;

import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.entities.MarcaEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import edu.tin.tingeso1.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;
@Service
public class OficinaRRH {
    //calcular años de servicio
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    public Integer calcularAniosServicio(String rut){
        EmpleadoEntity empleado=empleadoRepository.findByRut(rut);
        return obtenerAniosServicio(empleado);
    }
    public Integer obtenerAniosServicio(EmpleadoEntity empleado){
        Date date= empleado.getFechaIngreso();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        return (2022-year);
    }

    public int ingresarJusHorExtra(String rut, Date fecha){
        MarcaEntity  marca= marcaRepository.findByHoraAfterAndRut(rut,fecha);
        if(marca == null){
            return 1;
        }
        else{
            obtenerHorasExtras(rut,marca );
            return 0;
        }
    }
    public void obtenerHorasExtras(String rut,MarcaEntity  marca){
        Integer horas=0;
        Integer hora = marca.getHora().getHours();
        horas = hora - 18;
        empleadoRepository.updateHorasExtras(rut,horas);
    }

    public Integer ingresarJustificativo(String rut,String fecha) throws ParseException {
        Date dia = Date.valueOf(fecha);
        MarcaEntity marca= marcaRepository.findByRutAndFecha(dia,rut);
        if(marca == null){
            return -1;
        }
        if(marca.getJustificativo() == Boolean.TRUE){
            return -2;
        }
        Integer i = obteneringresarJustificativo(marca);
        if(i==1){
            marcaRepository.updateJustificativo(dia,rut);
            return 1;
        }
        else{
            return 0;
        }
    }

    public Integer obteneringresarJustificativo(MarcaEntity marca) throws ParseException {
        if( marca.getHora().compareTo(Time.valueOf("09:10:00"))> 0){
            return 1;
        }return 0;
    }

    //calcular descuento fijo
    public double calcularDescuento(String rut){
        ArrayList<MarcaEntity> marcas = marcaRepository.findByHoraIngrAndRut(rut);
        return obtenerDescuento( marcas );
    }
    public double obtenerDescuento(ArrayList<MarcaEntity> marcas ) {
        double descuento = 0;
        for (MarcaEntity marca : marcas) {
            Integer hor = (marca.getHora().getHours() -8)*60;
            Integer min = marca.getHora().getMinutes()+hor;
            if (Boolean.FALSE.equals(marca.getJustificativo()) ) {
                if (min > 70) {
                    descuento = descuento + 0.15;
                }else if (min > 45) {
                    descuento = descuento + 0.06;
                } else if (min > 25) {
                    descuento = descuento + 0.03;
                } else if (min > 10) {
                    descuento = descuento + 0.1;
                }
            }
        }
        return descuento;
    }


    //Calcula bonificacion por numero de horas extras
    //Categoria "A":
    //Categoria "B":
    //Categoria "C":
    public Integer calcularBonificacionHorasExtras(String rut){
        EmpleadoEntity empleado=empleadoRepository.findByRut(rut);
        return obtenerBonifHorasExtras( empleado);
    }
    public Integer obtenerBonifHorasExtras( EmpleadoEntity empleado) {
        Integer bonificacionPorHorasExtras = 0;
        Integer numHorasExtras;
        if(empleado.getHorasExtras() == null){
            numHorasExtras = 0;
        }
        else{
            numHorasExtras= empleado.getHorasExtras();

        }
        if(empleado.getCategoria().equals("A") ) {
            bonificacionPorHorasExtras = numHorasExtras * 25000;
        }
        else if(empleado.getCategoria().equals("B")) {
            bonificacionPorHorasExtras = numHorasExtras * 20000;
        }
        else if(empleado.getCategoria().equals("C")) {
            bonificacionPorHorasExtras = numHorasExtras * 100000;
        }
        else {
            bonificacionPorHorasExtras = 0;
        }
        return bonificacionPorHorasExtras;

    }

    //calcular bonificacion por tiempo de servicio
    public double calcularBonificacionTiempoServicio(String rut) {
        double bonificacionPorFechaIngreso = 0.0;
        Integer anios = calcularAniosServicio(rut);

        if (anios < 10 && anios >= 5) {
                bonificacionPorFechaIngreso =  0.05;
        }
        else if (anios < 15 && anios >= 10) {
                bonificacionPorFechaIngreso =  0.08;
        }
        else if (anios < 20 && anios >= 15) {
                bonificacionPorFechaIngreso =  0.11;
        }
        else if (anios < 25 && anios >= 20) {
                bonificacionPorFechaIngreso =  0.14;

        }else if (anios >= 25) {
                bonificacionPorFechaIngreso = 0.17;
            }

        return bonificacionPorFechaIngreso;
    }

    //calcular Sueldo
    public double calcularSueldoFinal(double sueldoFijo, String rut){
        return obtenerSueldoFinal(sueldoFijo, rut);
    }

    public double sueldoFijo(String rut){
        EmpleadoEntity empleado = empleadoRepository.findByRut(rut);
        return obtenerSueldoFijo(empleado);
    }
    public double obtenerSueldoFijo( EmpleadoEntity empleado) {
        if(empleado.getCategoria().equals("A")){
            return 1700000;
        }
        else if(empleado.getCategoria().equals("B")){
            return 1200000;
        }
        else if(empleado.getCategoria().equals("C")){
            return 800000;
        }
        else{
            return 0;
        }
    }


    public double obtenerSueldoFinal(double sueldoFijo,String rut) {
        double sueldo = 0;
        sueldo= sueldoFijo(rut)+(sueldoFijo*calcularBonificacionTiempoServicio(rut))+ calcularBonificacionHorasExtras(rut) - (sueldoFijo*calcularDescuento(rut));
        double cot=0.10;
        double cot2=0.08;
        return (sueldo - (sueldo * cot) - (sueldo * cot2));//descuentos fijos cotizacion
    }

    public void update(){
        List<EmpleadoEntity> empleados= empleadoRepository.findAll();
        for(EmpleadoEntity empleado:empleados){
            String rut= empleado.getRut();
            Integer aniosServicio = calcularAniosServicio(rut);//si
            Integer horasExtras = calcularBonificacionHorasExtras(rut);//si
            double sueldoFijo = sueldoFijo(rut);//si
            double descuento = calcularDescuento(rut)*sueldoFijo;//si
            double bonificacion = calcularBonificacionTiempoServicio(rut)* sueldoFijo;
            double sueldoBruto = sueldoFijo+horasExtras+bonificacion; // Sueldo bruto como se ve en la formula es solo las sumas
            double previsional = (sueldoBruto - descuento)*0.10;
            double salud = (sueldoBruto - descuento)*0.08;
            double sueldoFinal = calcularSueldoFinal(sueldoFijo,rut);
            empleadoRepository.updateByOne(rut,aniosServicio,horasExtras,descuento,bonificacion,sueldoFijo,sueldoBruto,previsional, salud, sueldoFinal);
        }
    }



}
