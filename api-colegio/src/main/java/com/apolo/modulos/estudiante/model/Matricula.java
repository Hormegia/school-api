package com.apolo.modulos.estudiante.model;



import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "Matricula de estudiante")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("matriculas")
    private Estudiante estudiante;

    @ManyToOne()
    private Grado grado;

    private Boolean estado;

    private Boolean jornada;

    @ManyToOne()
    private PeriodoAcademico periodoAcademico;

    public Matricula() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
