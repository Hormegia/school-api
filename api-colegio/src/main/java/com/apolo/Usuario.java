package com.apolo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel(description = "detalles usuario")
public class Usuario {

    private Integer id;

    @Size(min=2, message = "minimo 2")
    private String nombre;

    private String correo;


    public Usuario(Integer id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
