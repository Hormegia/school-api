package com.apolo.service;

import com.apolo.model.Rol;
import com.apolo.model.RolUsuario;
import com.apolo.repository.RolUsuarioRepository;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
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

    private final RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenActivacionUsuarioRepository tokenActivacionUsuarioRepository, RolUsuarioRepository rolUsuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenActivacionUsuarioRepository = tokenActivacionUsuarioRepository;
        this.rolUsuarioRepository = rolUsuarioRepository;
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
    public RolUsuario agregarRolUsuario(RolUsuario rolUsuario, int id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);


        if(!usuarioOptional.isPresent()){
            throw new ObjetoNoEncontradoException("No se encuentra el rol con el id:" + id);
        }

        Usuario usuario = usuarioOptional.get();
        if(usuario.getId() != rolUsuario.getUsuario().getId())
            throw new ErrorGeneralExcepcion("No coinciden los id del usuario y del rol");


        return rolUsuarioRepository.save(rolUsuario);
    }

    @Override
    public void eliminarRolUsuario(RolUsuario rolUsuario, int id) {
        rolUsuarioRepository.deleteById(rolUsuario.getId());
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Integer idUsuario = usuario.getId();

        if(idUsuario == null)
            throw new ObjetoNoEncontradoException("Se debe enviar un Id");

        Optional<Usuario> ususarioOption = userRepository.findById(idUsuario);

        if(!ususarioOption.isPresent())
            throw new ObjetoNoEncontradoException(String.format("No existe un usuario con el id %s", idUsuario));

        Usuario usuarioDb = ususarioOption.get();

        if(usuarioDb.getCorreo().equals(usuario.getCorreo()))
            throw new ErrorGeneralExcepcion("No se le puede cambiar el correo al usuario");

        return userRepository.save(usuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        if(existeCorreo(usuario.getCorreo()))
            throw new ObjetoNoEncontradoException("ya existe un registro con estos valores");

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return userRepository.save(usuario);
    }



}
