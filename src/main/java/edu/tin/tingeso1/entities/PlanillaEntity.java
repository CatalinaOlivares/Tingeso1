package edu.tin.tingeso1.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//entidades
@Entity
@Table(name = "planilla")
//lombok
@Data//@ -> geters y setters
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String rut;
    private String nombreEmpleado ;
    private String categoria;
    private Integer aniosServicio ;
    private Integer sueldoFijoMensual;
    private Integer bonificacionAniosServicio;
    private Integer pagoHorasExtras;
    private Integer descuentos;
    private Integer sueldoBruto;
    private Integer cotizacionPrevisional;
    private Integer cotizacionSalud;
    private Integer sueldoFinal;
}
