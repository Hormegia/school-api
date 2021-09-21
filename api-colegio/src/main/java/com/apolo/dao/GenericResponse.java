package com.apolo.dao;

import java.io.Serializable;

public class GenericResponse implements Serializable {

    private String mensaje;

    public GenericResponse() {};

    public String getMensaje() {
        return mensaje;
    };

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    };

}
