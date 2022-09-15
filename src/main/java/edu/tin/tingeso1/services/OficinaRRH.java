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
        return ObtenerAniosServicio(rut, empleado);
    }
    public Integer ObtenerAniosServicio(String rut,EmpleadoEntity empleado){
        Date date= empleado.getFechaIngreso();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int aniosServicio=(2022-year);
        return aniosServicio;
    }

    public void ingresar_jus_horas_extras(String rut, Date fecha){
        MarcaEntity  marca= marcaRepository.findByHoraAfterAndRut(rut,fecha);
        ObtenerHorasExtras(rut,marca );
    }
    public void ObtenerHorasExtras(String rut,MarcaEntity  marca){
        Integer horas=0;
        Integer hora = marca.getHora().getHours();
        horas = hora - 18;
        empleadoRepository.updateHoras_Extras(rut,horas);
    }

    public int ingresarJustificativo(String rut,String fecha) throws ParseException {
        Date dia = Date.valueOf(fecha);
        MarcaEntity marca= marcaRepository.findByRutAndFecha(dia,rut);
        if(marca == null){
            return -1;
        }
        if(marca.getJustificativo() == Boolean.TRUE){
            return -2;
        }
        Integer i = ObteneringresarJustificativo(rut, dia ,marca);
        if(i==1){
            marcaRepository.updateJustificativo(dia,rut);
            return 1;
        }
        else{
            return 0;
        }
    }

    public Integer ObteneringresarJustificativo(String rut,Date fecha,MarcaEntity marca) throws ParseException {
        if( marca.getHora().compareTo(Time.valueOf("09:10:00"))> 0){
            return 1;
        }return 0;
    }

    //calcular descuento fijo
    public double calcularDescuento(String rut){
        ArrayList<MarcaEntity> marcas = marcaRepository.findByHoraIngrAndRut(rut);
        return ObtenerDescuento(rut,  marcas );
    }
    public double ObtenerDescuento(String rut,ArrayList<MarcaEntity> marcas ) {
        double descuento = 0;
        boolean revisada=false;
        for (MarcaEntity marca : marcas) {
            Integer hor = (marca.getHora().getHours() -8)*60;
            Integer min = marca.getHora().getMinutes()+hor;
            if (marca.getJustificativo() && min > 70) {
                return descuento = descuento + 0;
            } else {
                if (min > 10 && min < 25) {
                    descuento = descuento + 0.01;
                }
                if (min > 25 && min < 45) {
                    descuento = descuento + 0.03;
                }
                if (min > 45 && min <= 70) {
                    descuento = descuento + 0.06;
                } else {
                    descuento = descuento + 0.15;
                }
            }
        }
        return descuento;
    }


    //Calcula bonificacion por numero de horas extras
    //Categoria "A":
    //Categoria "B":
    //Categoria "C":
    public double calcularBonificacionHorasExtras(String rut){
        EmpleadoEntity empleado=empleadoRepository.findByRut(rut);
        return ObtenerBonifHorasExtras(rut, empleado);
    }
    public double ObtenerBonifHorasExtras(String rut, EmpleadoEntity empleado) {
        double bonificacionPorHorasExtras = 0;
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
        } else {
            bonificacionPorHorasExtras = numHorasExtras * 10000;
        }
        return bonificacionPorHorasExtras;

    }

    //calcular bonificacion por tiempo de servicio
    public double calcularBonificcionTiempoServicio(String rut) {
        double bonificacionPorFechaIngreso = 0;
        Integer anios = calcularAniosServicio(rut);
        if (anios < 5) {
            return bonificacionPorFechaIngreso;
        } else {
            if (anios == 5 && anios < 10) {
                bonificacionPorFechaIngreso = anios * 0.05;
            }
            if (anios == 10 && anios < 15) {
                bonificacionPorFechaIngreso = anios * 0.08;
            }
            if (anios == 15 && anios < 20) {
                bonificacionPorFechaIngreso = anios * 0.11;
            }
            if (anios == 20 && anios < 25) {
                bonificacionPorFechaIngreso = anios * 0.14;
            } else {
                bonificacionPorFechaIngreso = anios * 0.17;
            }
        }
        return bonificacionPorFechaIngreso;
    }

    //calcular Sueldo
    public double calcularSueldoFinal(double sueldoFijo, String rut){
        EmpleadoEntity empleado=empleadoRepository.findByRut(rut);
        return ObtenerSueldoFinal(sueldoFijo, rut, empleado);
    }

    public double sueldoFijo(String rut){
        EmpleadoEntity empleado = empleadoRepository.findByRut(rut);
        if(empleado.getCategoria() == "A"){
            return 1700000;
        }
        else if(empleado.getCategoria() == "B"){
            return 1200000;
        }
        else{
            return 800000;
        }
    }

    public double ObtenerSueldoFinal(double sueldoFijo,String rut,EmpleadoEntity empleado) {
        double sueldo = 0;
        sueldo= sueldoFijo(rut)+calcularBonificcionTiempoServicio(rut)+ calcularBonificacionHorasExtras(rut) - (sueldoFijo*calcularDescuento(rut));
        double cot=0.10;
        double cot2=0.08;
        double sueldoFinal= sueldo - (sueldo * cot) - (sueldo * cot2);//descuentos fijos cotizacion
        return sueldoFinal;
    }

    public void Update(){
        List<EmpleadoEntity> empleados= empleadoRepository.findAll();
        for(EmpleadoEntity empleado:empleados){
            String rut= empleado.getRut();
            Integer aniosServicio = calcularAniosServicio(rut);//si
            double horasExtras = calcularBonificacionHorasExtras(rut);//si
            double descuento = calcularDescuento(rut);
            double bonificacion = calcularBonificcionTiempoServicio(rut);
            double sueldoFijo = sueldoFijo(rut);//si
            double sueldoBruto = (double) sueldoFijo+horasExtras+bonificacion; // Sueldo bruto como se ve en la formula es solo las sumas
            double previsional = (sueldoBruto - sueldoFijo*descuento)*0.10;
            double salud = (sueldoBruto - sueldoFijo*descuento)*0.08;
            double sueldoFinal = calcularSueldoFinal(sueldoFijo,rut);
            empleadoRepository.updateByOne(rut,aniosServicio,horasExtras,descuento,bonificacion,sueldoFijo,sueldoBruto,previsional, salud, sueldoFinal);
        }
    }



}
