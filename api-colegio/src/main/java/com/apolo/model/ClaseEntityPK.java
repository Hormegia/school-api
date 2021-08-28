package com.apolo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ClaseEntityPK implements Serializable {
    private int asignaturaId;
    private int asignaturaGradoId;
    private int profesorId;
    private int sedeId;

    @Column(name = "asignatura_id", nullable = false)
    @Id
    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Column(name = "asignatura_grado_id", nullable = false)
    @Id
    public int getAsignaturaGradoId() {
        return asignaturaGradoId;
    }

    public void setAsignaturaGradoId(int asignaturaGradoId) {
        this.asignaturaGradoId = asignaturaGradoId;
    }

    @Column(name = "profesor_id", nullable = false)
    @Id
    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    @Column(name = "sede_id", nullable = false)
    @Id
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
        ClaseEntityPK that = (ClaseEntityPK) o;
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
