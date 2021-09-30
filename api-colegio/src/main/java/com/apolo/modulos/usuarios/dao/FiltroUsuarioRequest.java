package com.apolo.modulos.usuarios.dao;

import java.io.Serializable;

public class FiltroUsuarioRequest implements Serializable {

    private boolean esColaborador;

    public FiltroUsuarioRequest() {
    }

    public boolean getEsColaborador() {
        return esColaborador;
    }

    public void setEsColaborador(boolean esColaborador) {
        this.esColaborador = esColaborador;
    }

}
