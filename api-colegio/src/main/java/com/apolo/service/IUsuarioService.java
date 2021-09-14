package com.apolo.service;

import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);
    TokenActivacionUsuario getTokenActivacion (String token);

    void crearTokenActivacion (Usuario usuario, String token);

    Usuario getUsuario(String token);
}
