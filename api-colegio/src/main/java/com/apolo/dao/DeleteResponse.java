package com.apolo.dao;

import java.io.Serializable;

public class DeleteResponse implements Serializable {
    private String respuesta = "Eliminado Correctamente";

    private Long id;

    public DeleteResponse(Long id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
