package com.apolo.modulos.roles.model;

import com.apolo.modulos.roles.enums.Credencial;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ApiModel(description = "Roles creados en el Sistema")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=2, message = "minimo 2")
    @NotNull
    private String nombre;

    @Column(unique = true)
    @NotNull
    private Credencial credencial;

    public Rol(){

    }

    public Rol(Long id, String nombre, Credencial credencial) {
        this.id = id;
        this.nombre = nombre;
        this.credencial = credencial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
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
