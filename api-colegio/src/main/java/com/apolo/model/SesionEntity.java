package com.apolo.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "sesion", schema = "colegio_corral", catalog = "")
public class SesionEntity {
    private int id;
    private String horaInicio;
    private String horaFin;
    private byte[] lunes;
    private byte[] martes;
    private byte[] miercoles;
    private byte[] jueves;
    private byte[] viernes;
    private HorarioAsignaturaEntity horarioAsignaturaByHorarioAsignaturaId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hora_inicio", nullable = true, length = 45)
    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Basic
    @Column(name = "hora_fin", nullable = true, length = 45)
    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Basic
    @Column(name = "lunes", nullable = true)
    public byte[] getLunes() {
        return lunes;
    }

    public void setLunes(byte[] lunes) {
        this.lunes = lunes;
    }

    @Basic
    @Column(name = "martes", nullable = true)
    public byte[] getMartes() {
        return martes;
    }

    public void setMartes(byte[] martes) {
        this.martes = martes;
    }

    @Basic
    @Column(name = "miercoles", nullable = true)
    public byte[] getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(byte[] miercoles) {
        this.miercoles = miercoles;
    }

    @Basic
    @Column(name = "jueves", nullable = true)
    public byte[] getJueves() {
        return jueves;
    }

    public void setJueves(byte[] jueves) {
        this.jueves = jueves;
    }

    @Basic
    @Column(name = "viernes", nullable = true)
    public byte[] getViernes() {
        return viernes;
    }

    public void setViernes(byte[] viernes) {
        this.viernes = viernes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SesionEntity that = (SesionEntity) o;
        return id == that.id &&
                Objects.equals(horaInicio, that.horaInicio) &&
                Objects.equals(horaFin, that.horaFin) &&
                Arrays.equals(lunes, that.lunes) &&
                Arrays.equals(martes, that.martes) &&
                Arrays.equals(miercoles, that.miercoles) &&
                Arrays.equals(jueves, that.jueves) &&
                Arrays.equals(viernes, that.viernes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, horaInicio, horaFin);
        result = 31 * result + Arrays.hashCode(lunes);
        result = 31 * result + Arrays.hashCode(martes);
        result = 31 * result + Arrays.hashCode(miercoles);
        result = 31 * result + Arrays.hashCode(jueves);
        result = 31 * result + Arrays.hashCode(viernes);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "horario_asignatura_id", referencedColumnName = "id", nullable = false)
    public HorarioAsignaturaEntity getHorarioAsignaturaByHorarioAsignaturaId() {
        return horarioAsignaturaByHorarioAsignaturaId;
    }

    public void setHorarioAsignaturaByHorarioAsignaturaId(HorarioAsignaturaEntity horarioAsignaturaByHorarioAsignaturaId) {
        this.horarioAsignaturaByHorarioAsignaturaId = horarioAsignaturaByHorarioAsignaturaId;
    }
}
