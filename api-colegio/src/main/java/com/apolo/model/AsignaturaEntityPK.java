package com.apolo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AsignaturaEntityPK implements Serializable {
    private int id;
    private int gradoId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "grado_id", nullable = false)
    @Id
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
        AsignaturaEntityPK that = (AsignaturaEntityPK) o;
        return id == that.id &&
                gradoId == that.gradoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradoId);
    }
}
