package com.apolo.service;

import com.apolo.model.Rol;
import com.apolo.model.RolUsuario;
import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);
    TokenActivacionUsuario getTokenActivacion (String token);

    void crearTokenActivacion (Usuario usuario, String token);

    Usuario getUsuario(String token);

    RolUsuario agregarRolUsuario(RolUsuario rolUsuario, int id);

    void eliminarRolUsuario(RolUsuario rolUsuario, int id);
}
