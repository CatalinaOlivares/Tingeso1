package edu.tin.tingeso1.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "marcas")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date fecha;
    private Time hora;
    private String rut;
    private Boolean justificativo;


    //constructor
    public MarcaEntity(Date date, Time hora, String rut){
        this.fecha=date;
        this.hora=hora;
        this.rut=rut;
        this.justificativo=Boolean.FALSE;
    }

    public MarcaEntity(Date date, Time hora, String rut, Boolean justificativo) {
        this.fecha=date;
        this.hora=hora;
        this.rut=rut;
        this.justificativo=justificativo;
    }



}

