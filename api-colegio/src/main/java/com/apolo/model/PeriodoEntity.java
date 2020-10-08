package com.apolo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "periodo", schema = "colegio_corral", catalog = "")
public class PeriodoEntity {
    private int id;
    private String fechaInicio;
    private String fechaFin;
    private String porcentaje;
    private Timestamp fechaPublicacion;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha_inicio", nullable = true, length = 45)
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin", nullable = true, length = 45)
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Basic
    @Column(name = "porcentaje", nullable = true, length = 45)
    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Basic
    @Column(name = "fecha_publicacion", nullable = true)
    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoEntity that = (PeriodoEntity) o;
        return id == that.id &&
                Objects.equals(fechaInicio, that.fechaInicio) &&
                Objects.equals(fechaFin, that.fechaFin) &&
                Objects.equals(porcentaje, that.porcentaje) &&
                Objects.equals(fechaPublicacion, that.fechaPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio, fechaFin, porcentaje, fechaPublicacion);
    }
}
