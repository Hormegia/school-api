package com.apolo.modulos.estudiante.model;

import com.apolo.modulos.grados.model.Grado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "información educativa de años pasados")
public class InformacionEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 4)
    private String fecha;

    private String ciudad;

    private String institucion;


    @ManyToOne(cascade = {CascadeType.ALL})
    private Grado grado;

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties()
    private Matricula matricula;
    public InformacionEducativa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
