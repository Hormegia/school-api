package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clase", schema = "colegio_corral", catalog = "")
@IdClass(ClaseEntityPK.class)
public class ClaseEntity {
    private int asignaturaId;
    private int asignaturaGradoId;
    private int profesorId;
    private int sedeId;

    @Id
    @Column(name = "asignatura_id", nullable = false)
    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Id
    @Column(name = "asignatura_grado_id", nullable = false)
    public int getAsignaturaGradoId() {
        return asignaturaGradoId;
    }

    public void setAsignaturaGradoId(int asignaturaGradoId) {
        this.asignaturaGradoId = asignaturaGradoId;
    }

    @Id
    @Column(name = "profesor_id", nullable = false)
    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    @Id
    @Column(name = "sede_id", nullable = false)
    public int getSedeId() {
        return sedeId;
    }

    public void setSedeId(int sedeId) {
        this.sedeId = sedeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaseEntity that = (ClaseEntity) o;
        return asignaturaId == that.asignaturaId &&
                asignaturaGradoId == that.asignaturaGradoId &&
                profesorId == that.profesorId &&
                sedeId == that.sedeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignaturaId, asignaturaGradoId, profesorId, sedeId);
    }
}
