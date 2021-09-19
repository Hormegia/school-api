package com.apolo.dao;

import java.io.Serializable;

public class DeleteResponse implements Serializable {
    private String respuesta = "Eliminado Correctamente";

    private int id;

    public DeleteResponse( int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
