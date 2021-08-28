package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "horario_asignatura", schema = "colegio_corral", catalog = "")
public class HorarioAsignaturaEntity {
    private int id;
    private AsignaturaEntity asignatura;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioAsignaturaEntity that = (HorarioAsignaturaEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "asignatura_id", referencedColumnName = "id", nullable = false), @JoinColumn(name = "asignatura_grado_id", referencedColumnName = "grado_id", nullable = false)})
    public AsignaturaEntity getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(AsignaturaEntity asignatura) {
        this.asignatura = asignatura;
    }
}
