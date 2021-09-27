package com.apolo.modulos.estudiante.model;

import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.usuarios.model.Persona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * datos variables
 *
 * direccion
 * barrio
 * localidad
 * estrato
 * nivel sisben
 * puntaje sisben
 * aficiones o hobbies
 * cirugías
 * eps
 * ars
 * ips
 *
 */

@Table(
        uniqueConstraints=
        @UniqueConstraint(name = "UniqueDocumentoAndTipoDocumento", columnNames={"tipoDocumento", "documento"})
)

@Entity
@ApiModel(description = "Estudiante registrado")
public class Estudiante extends Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    private String ciudad;

    private String departamento;

    private String ciudadExpedicion;

    private String grupo_sanguineo;

    private boolean rh;

    @ManyToOne()
    @JsonIgnoreProperties(value = {"usuario", "estudiante", "acudiente"})
    private Acudiente acudiente;


    public Estudiante() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }

    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }

    public boolean isRh() {
        return rh;
    }

    public void setRh(boolean rh) {
        this.rh = rh;
    }
}
