package com.apolo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "estudiante", schema = "colegio_corral", catalog = "")
public class EstudianteEntity {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private Timestamp fechaNacimiento;
    private String genero;
    private AcudienteEntity acudienteByAcudienteId;

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
    @Column(name = "apellido", nullable = true, length = 45)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
    @Column(name = "fecha_nacimiento", nullable = true)
    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Basic
    @Column(name = "genero", nullable = true, length = 45)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudianteEntity that = (EstudianteEntity) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellido, that.apellido) &&
                Objects.equals(correo, that.correo) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
                Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, correo, fechaNacimiento, genero);
    }

    @ManyToOne
    @JoinColumn(name = "acudiente_id", referencedColumnName = "id", nullable = false)
    public AcudienteEntity getAcudienteByAcudienteId() {
        return acudienteByAcudienteId;
    }

    public void setAcudienteByAcudienteId(AcudienteEntity acudienteByAcudienteId) {
        this.acudienteByAcudienteId = acudienteByAcudienteId;
    }
}
