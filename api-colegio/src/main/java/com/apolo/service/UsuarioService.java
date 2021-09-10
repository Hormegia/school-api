package com.apolo.service;

import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;
import com.apolo.repository.TokenActivacionUsuarioRepository;
import com.apolo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenActivacionUsuarioRepository tokenActivacionUsuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenActivacionUsuarioRepository tokenActivacionUsuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenActivacionUsuarioRepository = tokenActivacionUsuarioRepository;
    }


    public boolean existeCorreo(String email){
        Optional<Usuario> usuario = userRepository.findUsuarioByCorreo(email);
        return usuario.isPresent();
    }

    @Override
    public TokenActivacionUsuario getTokenActivacion(String token) {
        return tokenActivacionUsuarioRepository.findByToken(token);
    }

    @Override
    public void crearTokenActivacion(Usuario usuario, String token) {
        TokenActivacionUsuario tokenActivacionUsuario = new TokenActivacionUsuario(usuario, token);
        tokenActivacionUsuarioRepository.save(tokenActivacionUsuario);
    }

    @Override
    public Usuario getUsuario(String token) {
        return tokenActivacionUsuarioRepository.findByToken(token).getUsuario();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        userRepository.save(usuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        if(existeCorreo(usuario.getCorreo()))
            throw new ObjetoNoEncontradoException("ya existe un registro con estos valores");

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return userRepository.save(usuario);
    }
}
