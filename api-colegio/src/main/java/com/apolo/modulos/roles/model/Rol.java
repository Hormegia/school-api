package com.apolo.modulos.roles.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ApiModel(description = "Roles creados en el Sistema")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=2, message = "minimo 2")
    @NotNull
    private String nombre;

    @Column(unique = true)
    @NotNull
    private String credencial;

    public Rol(){

    }

    public Rol(Integer id, String nombre, String credencial) {
        this.id = id;
        this.nombre = nombre;
        this.credencial = credencial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rol{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", credencial='").append(credencial).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
