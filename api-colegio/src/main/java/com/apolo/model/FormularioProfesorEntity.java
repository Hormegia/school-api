package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_profesor", schema = "colegio_corral", catalog = "")
public class FormularioProfesorEntity {
    private int id;
    private String direccion;
    private String telefonoContacto;
    private String telefono;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "direccion", nullable = true, length = 45)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "telefono_contacto", nullable = true, length = 45)
    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @Basic
    @Column(name = "telefono", nullable = true, length = 45)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioProfesorEntity that = (FormularioProfesorEntity) o;
        return id == that.id &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(telefonoContacto, that.telefonoContacto) &&
                Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, direccion, telefonoContacto, telefono);
    }
}
