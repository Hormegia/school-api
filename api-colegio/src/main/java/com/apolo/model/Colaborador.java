package com.apolo.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Colaborador Interno")
public class Colaborador extends Persona{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Colaborador() {
        super();
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
