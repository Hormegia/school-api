package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "campo_formulario", schema = "colegio_corral", catalog = "")
public class CampoFormularioEntity {
    private int id;
    private String nombre;
    private String campoFormulariocol;

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
    @Column(name = "campo_formulariocol", nullable = true, length = 45)
    public String getCampoFormulariocol() {
        return campoFormulariocol;
    }

    public void setCampoFormulariocol(String campoFormulariocol) {
        this.campoFormulariocol = campoFormulariocol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampoFormularioEntity that = (CampoFormularioEntity) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(campoFormulariocol, that.campoFormulariocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, campoFormulariocol);
    }
}
