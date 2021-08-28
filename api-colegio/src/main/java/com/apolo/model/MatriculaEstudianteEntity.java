package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "matricula_estudiante", schema = "colegio_corral", catalog = "")
@IdClass(MatriculaEstudianteEntityPK.class)
public class MatriculaEstudianteEntity {
    private int id;
    private int periodoAcademicoId;
    private int estudianteId;
    private int asistenciaEstudianteClaseAsignaturaId;
    private int asistenciaEstudianteClaseAsignaturaGradoId;
    private Integer estado;
    private EstudianteEntity estudianteByEstudianteId1;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "periodo_academico_id", nullable = false)
    public int getPeriodoAcademicoId() {
        return periodoAcademicoId;
    }

    public void setPeriodoAcademicoId(int periodoAcademicoId) {
        this.periodoAcademicoId = periodoAcademicoId;
    }

    @Id
    @Column(name = "estudiante_id", nullable = false)
    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Id
    @Column(name = "asistencia_estudiante_clase_asignatura_id", nullable = false)
    public int getAsistenciaEstudianteClaseAsignaturaId() {
        return asistenciaEstudianteClaseAsignaturaId;
    }

    public void setAsistenciaEstudianteClaseAsignaturaId(int asistenciaEstudianteClaseAsignaturaId) {
        this.asistenciaEstudianteClaseAsignaturaId = asistenciaEstudianteClaseAsignaturaId;
    }

    @Id
    @Column(name = "asistencia_estudiante_clase_asignatura_grado_id", nullable = false)
    public int getAsistenciaEstudianteClaseAsignaturaGradoId() {
        return asistenciaEstudianteClaseAsignaturaGradoId;
    }

    public void setAsistenciaEstudianteClaseAsignaturaGradoId(int asistenciaEstudianteClaseAsignaturaGradoId) {
        this.asistenciaEstudianteClaseAsignaturaGradoId = asistenciaEstudianteClaseAsignaturaGradoId;
    }

    @Basic
    @Column(name = "estado", nullable = true)
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatriculaEstudianteEntity that = (MatriculaEstudianteEntity) o;
        return id == that.id &&
                periodoAcademicoId == that.periodoAcademicoId &&
                estudianteId == that.estudianteId &&
                asistenciaEstudianteClaseAsignaturaId == that.asistenciaEstudianteClaseAsignaturaId &&
                asistenciaEstudianteClaseAsignaturaGradoId == that.asistenciaEstudianteClaseAsignaturaGradoId &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodoAcademicoId, estudianteId, asistenciaEstudianteClaseAsignaturaId, asistenciaEstudianteClaseAsignaturaGradoId, estado);
    }

    @ManyToOne
    @JoinColumn(name = "estudiante_id1", referencedColumnName = "id", nullable = false)
    public EstudianteEntity getEstudianteByEstudianteId1() {
        return estudianteByEstudianteId1;
    }

    public void setEstudianteByEstudianteId1(EstudianteEntity estudianteByEstudianteId1) {
        this.estudianteByEstudianteId1 = estudianteByEstudianteId1;
    }
}
