package com.apolo.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@ApiModel(description = "Roles creados en el Sistema")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=2, message = "minimo 2")
    private String nombre;


    private String credencial;

    public Rol(){

    }

    public Rol(Integer id, String nombre, String credencial) {
        this.id = id;
        this.nombre = nombre;
        this.credencial = credencial;
    }
}
