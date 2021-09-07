package com.apolo.model.service;

import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;
import com.apolo.model.repository.UserRepository;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);
    TokenActivacionUsuario getTokenActivacion (String token);

    void crearTokenActivacion (Usuario usuario, String token);

    Usuario getUsuario(String token);
}
