package com.apolo.model.repository;

import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenActivacionUsuarioRepository extends JpaRepository<TokenActivacionUsuario, Long> {

    TokenActivacionUsuario findByToken(String token);

    TokenActivacionUsuario findByUsuario(Usuario usuario);
}
