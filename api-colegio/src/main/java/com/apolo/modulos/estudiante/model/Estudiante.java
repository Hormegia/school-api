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

    private String departamentoExpedicion;

    private String ciudadExpedicion;

    private String grupoSanguineo;

    private String rh;

    private String direccion;

    private String barrio;

    private String localidad;

    private String estrato;

    private String nivelSisben;

    private String puntajeSisben;

    private String aficiones;

    private String cirugias;

    private String eps;

    private String ars;

    private String ips;


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
        return grupoSanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupoSanguineo = grupo_sanguineo;
    }

    public String isRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getPuntajeSisben() {
        return puntajeSisben;
    }

    public void setPuntajeSisben(String puntajeSisben) {
        this.puntajeSisben = puntajeSisben;
    }

    public String getAficiones() {
        return aficiones;
    }

    public void setAficiones(String aficiones) {
        this.aficiones = aficiones;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArs() {
        return ars;
    }

    public void setArs(String ars) {
        this.ars = ars;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getDepartamentoExpedicion() {
        return departamentoExpedicion;
    }

    public void setDepartamentoExpedicion(String departamentoExpedicion) {
        this.departamentoExpedicion = departamentoExpedicion;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getRh() {
        return rh;
    }
}
