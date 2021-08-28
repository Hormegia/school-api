package com.apolo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "periodo_academico", schema = "colegio_corral", catalog = "")
public class PeriodoAcademicoEntity {
    private int id;
    private String nombre;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private FormularioMatriculaEntity formularioMatriculaByFormularioMatriculaPeriodoAcademicoId;
    private FormularioObservadorEntity formularioObservadorByFormularioObservadorId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "fecha_inicio", nullable = true)
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin", nullable = true)
    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoAcademicoEntity that = (PeriodoAcademicoEntity) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(fechaInicio, that.fechaInicio) &&
                Objects.equals(fechaFin, that.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaInicio, fechaFin);
    }

    @ManyToOne
    @JoinColumn(name = "formulario_matricula_periodo_academico_id", referencedColumnName = "periodo_academico_id", nullable = false)
    public FormularioMatriculaEntity getFormularioMatriculaByFormularioMatriculaPeriodoAcademicoId() {
        return formularioMatriculaByFormularioMatriculaPeriodoAcademicoId;
    }

    public void setFormularioMatriculaByFormularioMatriculaPeriodoAcademicoId(FormularioMatriculaEntity formularioMatriculaByFormularioMatriculaPeriodoAcademicoId) {
        this.formularioMatriculaByFormularioMatriculaPeriodoAcademicoId = formularioMatriculaByFormularioMatriculaPeriodoAcademicoId;
    }

    @ManyToOne
    @JoinColumn(name = "formulario_observador_id", referencedColumnName = "id", nullable = false)
    public FormularioObservadorEntity getFormularioObservadorByFormularioObservadorId() {
        return formularioObservadorByFormularioObservadorId;
    }

    public void setFormularioObservadorByFormularioObservadorId(FormularioObservadorEntity formularioObservadorByFormularioObservadorId) {
        this.formularioObservadorByFormularioObservadorId = formularioObservadorByFormularioObservadorId;
    }
}
