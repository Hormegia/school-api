package com.apolo.modulos.estudiante.model;



import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "Matricula de estudiante")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("matriculas")
    private Estudiante estudiante;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Grado grado;

    private Boolean estado;

    private Boolean jornada;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private PeriodoAcademico periodoAcademico;

    public Matricula() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isJornada() {
        return jornada;
    }

    public void setJornada(boolean jornada) {
        this.jornada = jornada;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getJornada() {
        return jornada;
    }

    public void setJornada(Boolean jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Matricula{");
        sb.append("id=").append(id);
        sb.append(", estudiante=").append(estudiante);
        sb.append(", grado=").append(grado);
        sb.append(", estado=").append(estado);
        sb.append(", jornada=").append(jornada);
        sb.append(", periodoAcademico=").append(periodoAcademico);
        sb.append('}');
        return sb.toString();
    }
}
