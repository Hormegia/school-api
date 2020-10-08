package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curso", schema = "colegio_corral", catalog = "")
public class CursoEntity {
    private String id;
    private int directorCursoId;

    @Id
    @Column(name = "id", nullable = false, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "director_curso_id", nullable = false)
    public int getDirectorCursoId() {
        return directorCursoId;
    }

    public void setDirectorCursoId(int directorCursoId) {
        this.directorCursoId = directorCursoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEntity that = (CursoEntity) o;
        return directorCursoId == that.directorCursoId &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, directorCursoId);
    }
}
