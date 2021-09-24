package com.apolo.model;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel(description = "detalle periodo")

public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=12, max=25)
    private String nombre;

    private Boolean estado;


    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacionPeriodo;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioInscripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinInscripcion;

    public Periodo (){this.estado =false;}

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacionPeriodo() {
        return fechaCreacionPeriodo;
    }

    public void setFechaCreacionPeriodo(Date fechaCreacionPeriodo) {
        this.fechaCreacionPeriodo = fechaCreacionPeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public Date getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(Date fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Periodo(Integer id, String nombre, Boolean estado, Date fechaCreacionPeriodo, Date fechaInicio, Date fechaFin, Date fechaInicioInscripcion, Date fechaFinInscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaCreacionPeriodo = fechaCreacionPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                ", fechaCreacionPeriodo=" + fechaCreacionPeriodo +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", fechaInicioInscripcion=" + fechaInicioInscripcion +
                ", fechaFinInscripcion=" + fechaFinInscripcion +
                '}';
    }
}