package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "profesor", schema = "colegio_corral", catalog = "")
public class ProfesorEntity {
    private int id;
    private String nombre;
    private int usuarioId;
    private int directorCursoId;
    private FormularioProfesorEntity formularioProfesorByFormularioProfesorId;
    private CursoEntity cursoByCursoId;

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
    @Column(name = "usuario_id", nullable = false)
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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
        ProfesorEntity that = (ProfesorEntity) o;
        return id == that.id &&
                usuarioId == that.usuarioId &&
                directorCursoId == that.directorCursoId &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, usuarioId, directorCursoId);
    }

    @ManyToOne
    @JoinColumn(name = "formulario_profesor_id", referencedColumnName = "id", nullable = false)
    public FormularioProfesorEntity getFormularioProfesorByFormularioProfesorId() {
        return formularioProfesorByFormularioProfesorId;
    }

    public void setFormularioProfesorByFormularioProfesorId(FormularioProfesorEntity formularioProfesorByFormularioProfesorId) {
        this.formularioProfesorByFormularioProfesorId = formularioProfesorByFormularioProfesorId;
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
