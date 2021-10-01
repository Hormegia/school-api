package com.apolo.modulos.usuarios.service;


import com.apolo.modulos.roles.repository.RolUsuario;
import com.apolo.modulos.usuarios.dao.FiltroUsuarioRequest;
import com.apolo.modulos.usuarios.model.TokenActivacionUsuario;
import com.apolo.modulos.usuarios.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);
    TokenActivacionUsuario getTokenActivacion (String token);

    void crearTokenActivacion (Usuario usuario, String token);

    Usuario getUsuario(String token);

    RolUsuario agregarRolUsuario(RolUsuario rolUsuario, Long id);

    void eliminarRolUsuario(RolUsuario rolUsuario, Long id);

    List<Usuario> obtenerUsuariosPorFiltro(Boolean esColaborador);
}
