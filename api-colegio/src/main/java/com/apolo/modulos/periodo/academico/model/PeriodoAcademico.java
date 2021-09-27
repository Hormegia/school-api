package com.apolo.modulos.periodo.academico.model;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel(description = "detalle periodo")

public class PeriodoAcademico {

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
    private Date fechaInicioInscripcion1;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinInscripcion1;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioInscripcion2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinInscripcion2;

    public PeriodoAcademico(){}

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

    public Date getFechaInicioInscripcion1() {
        return fechaInicioInscripcion1;
    }

    public void setFechaInicioInscripcion1(Date fechaInicioInscripcion) {
        this.fechaInicioInscripcion1 = fechaInicioInscripcion;
    }

    public Date getFechaFinInscripcion1() {
        return fechaFinInscripcion1;
    }

    public void setFechaFinInscripcion1(Date fechaFinInscripcion) {
        this.fechaFinInscripcion1 = fechaFinInscripcion;
    }

    public Date getFechaInicioInscripcion2() {
        return fechaInicioInscripcion2;
    }

    public void setFechaInicioInscripcion2(Date fechaInicioInscripcion2) {
        this.fechaInicioInscripcion2 = fechaInicioInscripcion2;
    }

    public Date getFechaFinInscripcion2() {
        return fechaFinInscripcion2;
    }

    public void setFechaFinInscripcion2(Date fechaFinInscripcion2) {
        this.fechaFinInscripcion2 = fechaFinInscripcion2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeriodoAcademico{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", estado=").append(estado);
        sb.append(", fechaCreacionPeriodo=").append(fechaCreacionPeriodo);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", fechaInicioInscripcion1=").append(fechaInicioInscripcion1);
        sb.append(", fechaFinInscripcion1=").append(fechaFinInscripcion1);
        sb.append(", fechaInicioInscripcion2=").append(fechaInicioInscripcion2);
        sb.append(", fechaFinInscripcion2=").append(fechaFinInscripcion2);
        sb.append('}');
        return sb.toString();
    }
}