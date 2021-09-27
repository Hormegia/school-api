package com.apolo.modulos.usuarios.dao;

import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.usuarios.model.Usuario;

import java.io.Serializable;

public class UsuarioAcudienteRequest implements Serializable {

    private Acudiente acudiente;

    private Usuario usuario;

    public UsuarioAcudienteRequest() {
    }

    public UsuarioAcudienteRequest(Acudiente acudiente, Usuario usuario) {
        this.acudiente = acudiente;
        this.usuario = usuario;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
