package com.apolo.modulos.usuarios.service;


import com.apolo.modulos.roles.repository.RolUsuario;
import com.apolo.modulos.usuarios.model.TokenActivacionUsuario;
import com.apolo.modulos.usuarios.model.Usuario;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);
    TokenActivacionUsuario getTokenActivacion (String token);

    void crearTokenActivacion (Usuario usuario, String token);

    Usuario getUsuario(String token);

    RolUsuario agregarRolUsuario(RolUsuario rolUsuario, int id);

    void eliminarRolUsuario(RolUsuario rolUsuario, int id);
}
