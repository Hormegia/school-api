package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "asignatura", schema = "colegio_corral", catalog = "")
@IdClass(AsignaturaEntityPK.class)
public class AsignaturaEntity {
    private int id;
    private String nombre;
    private int gradoId;

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

    @Id
    @Column(name = "grado_id", nullable = false)
    public int getGradoId() {
        return gradoId;
    }

    public void setGradoId(int gradoId) {
        this.gradoId = gradoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignaturaEntity that = (AsignaturaEntity) o;
        return id == that.id &&
                gradoId == that.gradoId &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, gradoId);
    }
}
