package com.apolo.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "Estudiante registrado")
public class Acudiente extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "acudiente")
    private Usuario usuario;


    public Acudiente() {
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
