package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sede", schema = "colegio_corral", catalog = "")
public class SedeEntity {
    private int id;
    private String nombre;
    private String direccion;

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
    @Column(name = "direccion", nullable = true, length = 45)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SedeEntity that = (SedeEntity) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(direccion, that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion);
    }
}
