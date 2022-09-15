package edu.tin.tingeso1.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

//entidades
@Entity
@Table(name = "empleados")
//lombok
@Data//@ -> geters y setters
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
//id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String rut;
    private String nombre;
    private String categoria;
    private Date fechaIngreso;
    private Integer aniosServicio;
    private Integer sueldoFijo;
    private Double bonificaAniosSer;
    private Integer horasExtras;
    private Double bonificaHorasExtras;
    private Double descuentos;
    private Double sueldoBruto;
    private Double cotPrev;
    private Double cotSal;
    private Double sueldoFinal;


}