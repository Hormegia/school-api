package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "campo_estudiante", schema = "colegio_corral", catalog = "")
public class CampoEstudianteEntity {
    private int id;
    private String valor;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "valor", nullable = true, length = 45)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampoEstudianteEntity that = (CampoEstudianteEntity) o;
        return id == that.id &&
                Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor);
    }
}
