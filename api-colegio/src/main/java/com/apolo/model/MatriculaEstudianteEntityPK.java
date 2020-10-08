package com.apolo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MatriculaEstudianteEntityPK implements Serializable {
    private int id;
    private int periodoAcademicoId;
    private int estudianteId;
    private int asistenciaEstudianteClaseAsignaturaId;
    private int asistenciaEstudianteClaseAsignaturaGradoId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "periodo_academico_id", nullable = false)
    @Id
    public int getPeriodoAcademicoId() {
        return periodoAcademicoId;
    }

    public void setPeriodoAcademicoId(int periodoAcademicoId) {
        this.periodoAcademicoId = periodoAcademicoId;
    }

    @Column(name = "estudiante_id", nullable = false)
    @Id
    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Column(name = "asistencia_estudiante_clase_asignatura_id", nullable = false)
    @Id
    public int getAsistenciaEstudianteClaseAsignaturaId() {
        return asistenciaEstudianteClaseAsignaturaId;
    }

    public void setAsistenciaEstudianteClaseAsignaturaId(int asistenciaEstudianteClaseAsignaturaId) {
        this.asistenciaEstudianteClaseAsignaturaId = asistenciaEstudianteClaseAsignaturaId;
    }

    @Column(name = "asistencia_estudiante_clase_asignatura_grado_id", nullable = false)
    @Id
    public int getAsistenciaEstudianteClaseAsignaturaGradoId() {
        return asistenciaEstudianteClaseAsignaturaGradoId;
    }

    public void setAsistenciaEstudianteClaseAsignaturaGradoId(int asistenciaEstudianteClaseAsignaturaGradoId) {
        this.asistenciaEstudianteClaseAsignaturaGradoId = asistenciaEstudianteClaseAsignaturaGradoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatriculaEstudianteEntityPK that = (MatriculaEstudianteEntityPK) o;
        return id == that.id &&
                periodoAcademicoId == that.periodoAcademicoId &&
                estudianteId == that.estudianteId &&
                asistenciaEstudianteClaseAsignaturaId == that.asistenciaEstudianteClaseAsignaturaId &&
                asistenciaEstudianteClaseAsignaturaGradoId == that.asistenciaEstudianteClaseAsignaturaGradoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodoAcademicoId, estudianteId, asistenciaEstudianteClaseAsignaturaId, asistenciaEstudianteClaseAsignaturaGradoId);
    }
}
