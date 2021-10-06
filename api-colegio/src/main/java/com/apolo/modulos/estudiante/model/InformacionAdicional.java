package com.apolo.modulos.estudiante.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "información adicional de la matricula")
public class InformacionAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean victimaCA;

    private Boolean sDesplazamiento;

    private Boolean desvinculadosGArmados;

    private Boolean hijoDesmovilizado;

    private String limitaciones;

    private String capacidadesExcepcionales;

    private String puntajeCI;

    private String examenVal;

    @OneToOne(cascade = {CascadeType.ALL})
    private Matricula matricula;

    public InformacionAdicional() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVictimaCA() {
        return victimaCA;
    }

    public void setVictimaCA(Boolean victimaCA) {
        this.victimaCA = victimaCA;
    }

    public Boolean getsDesplazamiento() {
        return sDesplazamiento;
    }

    public void setsDesplazamiento(Boolean sDesplazamineto) {
        this.sDesplazamiento = sDesplazamineto;
    }

    public Boolean getDesvinculadosGArmados() {
        return desvinculadosGArmados;
    }

    public void setDesvinculadosGArmados(Boolean desvinculadosGArmados) {
        this.desvinculadosGArmados = desvinculadosGArmados;
    }

    public Boolean getHijoDesmovilizado() {
        return hijoDesmovilizado;
    }

    public void setHijoDesmovilizado(Boolean hijoDesmovilizado) {
        this.hijoDesmovilizado = hijoDesmovilizado;
    }

    public String getLimitaciones() {
        return limitaciones;
    }

    public void setLimitaciones(String limitaciones) {
        this.limitaciones = limitaciones;
    }

    public String getCapacidadesExcepcionales() {
        return capacidadesExcepcionales;
    }

    public void setCapacidadesExcepcionales(String capacidadesExcepcionales) {
        this.capacidadesExcepcionales = capacidadesExcepcionales;
    }

    public String getPuntajeCI() {
        return puntajeCI;
    }

    public void setPuntajeCI(String puntajeCI) {
        this.puntajeCI = puntajeCI;
    }

    public String getExamenVal() {
        return examenVal;
    }

    public void setExamenVal(String examenVal) {
        this.examenVal = examenVal;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
