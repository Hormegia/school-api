package com.apolo.dao;

import com.apolo.model.Acudiente;
import com.apolo.model.Colaborador;
import com.apolo.model.Usuario;

public class UsuarioColaboradorRequest {

    private Colaborador colaborador;

    private Usuario usuario;

    public UsuarioColaboradorRequest() {
    }

    public UsuarioColaboradorRequest(Colaborador colaborador, Usuario usuario) {
        this.colaborador = colaborador;
        this.usuario = usuario;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
