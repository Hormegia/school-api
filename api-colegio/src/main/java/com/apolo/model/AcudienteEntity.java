package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "acudiente", schema = "colegio_corral", catalog = "")
public class AcudienteEntity {
    private int id;
    private String correo;
    private String nombre;
    private int usuarioId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "correo", nullable = true, length = 45)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
    @Column(name = "usuario_id", nullable = false)
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcudienteEntity that = (AcudienteEntity) o;
        return id == that.id &&
                usuarioId == that.usuarioId &&
                Objects.equals(correo, that.correo) &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, correo, nombre, usuarioId);
    }
}
