package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rol", schema = "colegio_corral", catalog = "")
public class RolEntity {
    private int id;
    private String nombre;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntity rolEntity = (RolEntity) o;
        return id == rolEntity.id &&
                Objects.equals(nombre, rolEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
