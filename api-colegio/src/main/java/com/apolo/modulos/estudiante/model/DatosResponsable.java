package com.apolo.modulos.estudiante.model;

import com.apolo.modulos.usuarios.model.Persona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "responsable de la matricula del estudiante")
public class DatosResponsable extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String numeroFijo;

    private String direccion;

    private String ocupacion;

    private String correo;

    private Boolean esPadre;

    private Boolean  esAcudiente;

    @OneToOne()
    @JsonIgnoreProperties()
    private Matricula matricula;

    public DatosResponsable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFijo() {
        return numeroFijo;
    }

    public void setNumeroFijo(String numeroFijo) {
        this.numeroFijo = numeroFijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEsPadre() {
        return esPadre;
    }

    public void setEsPadre(boolean esPadre) {
        this.esPadre = esPadre;
    }

    public boolean isEsAcudiente() {
        return esAcudiente;
    }

    public void setEsAcudiente(boolean esAcudiente) {
        this.esAcudiente = esAcudiente;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
