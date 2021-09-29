package com.apolo.modulos.estudiante.dao;

import com.apolo.modulos.estudiante.model.DatosResponsable;
import com.apolo.modulos.estudiante.model.InformacionAdicional;
import com.apolo.modulos.estudiante.model.InformacionEducativa;
import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.grados.model.Grado;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "Request para crear una matricula")
public class MatriculaEstudianteRequest implements Serializable {


    private DatosResponsable datosMadre;


    private DatosResponsable datosPadre;

    @NotNull
    private DatosResponsable datosAcudiente;


    private InformacionAdicional informacionAdicional;


    private InformacionEducativa informacionEducativa;

    @NotNull
    private Grado grado;

    @NotNull
    private Matricula matricula;

    public MatriculaEstudianteRequest() {
    }

    public DatosResponsable getDatosMadre() {
        return datosMadre;
    }

    public void setDatosMadre(DatosResponsable datosMadre) {
        this.datosMadre = datosMadre;
    }

    public DatosResponsable getDatosPadre() {
        return datosPadre;
    }

    public void setDatosPadre(DatosResponsable datosPadre) {
        this.datosPadre = datosPadre;
    }

    public DatosResponsable getDatosAcudiente() {
        return datosAcudiente;
    }

    public void setDatosAcudiente(DatosResponsable datosAcudiente) {
        this.datosAcudiente = datosAcudiente;
    }

    public InformacionAdicional getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(InformacionAdicional informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public InformacionEducativa getInformacionEducativa() {
        return informacionEducativa;
    }

    public void setInformacionEducativa(InformacionEducativa informacionEducativa) {
        this.informacionEducativa = informacionEducativa;
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
