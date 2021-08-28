package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "horario_curso", schema = "colegio_corral", catalog = "")
public class HorarioCursoEntity {
    private int id;
    private CursoEntity cursoByCursoId;

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
        HorarioCursoEntity that = (HorarioCursoEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    public CursoEntity getCursoByCursoId() {
        return cursoByCursoId;
    }

    public void setCursoByCursoId(CursoEntity cursoByCursoId) {
        this.cursoByCursoId = cursoByCursoId;
    }
}
