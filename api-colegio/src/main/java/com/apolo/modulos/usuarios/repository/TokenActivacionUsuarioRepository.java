package com.apolo.modulos.usuarios.repository;

import com.apolo.modulos.usuarios.model.TokenActivacionUsuario;
import com.apolo.modulos.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenActivacionUsuarioRepository extends JpaRepository<TokenActivacionUsuario, Integer> {

    TokenActivacionUsuario findByToken(String token);

    TokenActivacionUsuario findByUsuario(Usuario usuario);
}
