package com.apolo.modulos.usuarios.service;


import com.apolo.modulos.roles.repository.RolUsuario;
import com.apolo.modulos.roles.repository.RolUsuarioRepository;
import com.apolo.modulos.usuarios.dao.FiltroUsuarioRequest;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.modulos.usuarios.model.TokenActivacionUsuario;
import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.repository.TokenActivacionUsuarioRepository;
import com.apolo.modulos.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {


    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    private final TokenActivacionUsuarioRepository tokenActivacionUsuarioRepository;

    private final RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, TokenActivacionUsuarioRepository tokenActivacionUsuarioRepository, RolUsuarioRepository rolUsuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.tokenActivacionUsuarioRepository = tokenActivacionUsuarioRepository;
        this.rolUsuarioRepository = rolUsuarioRepository;
    }


    public boolean existeCorreo(String email){
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByCorreo(email);
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
    public RolUsuario agregarRolUsuario(RolUsuario rolUsuario, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);


        if(!usuarioOptional.isPresent()){
            throw new ObjetoNoEncontradoException("No se encuentra un usuario con el id:" + id);
        }

        Usuario usuario = usuarioOptional.get();
        if(usuario.getId() != rolUsuario.getUsuario().getId())
            throw new ErrorGeneralExcepcion("No coinciden los id del usuario y del rol");

        Usuario usuarioAtenticado = usuarioRepository.findUsuarioByCorreo(SecurityContextHolder.getContext().getAuthentication().getName().toString()).get();

        rolUsuario.setUsuarioCreacion(usuarioAtenticado);
        rolUsuario.setFechaCreacion(new Date());

        return rolUsuarioRepository.save(rolUsuario);
    }

    @Override
    public void eliminarRolUsuario(RolUsuario rolUsuario, Long id) {
        rolUsuarioRepository.deleteById(rolUsuario.getId());
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Long idUsuario = usuario.getId();

        if(idUsuario == null)
            throw new ObjetoNoEncontradoException("Se debe enviar un Id");

        Optional<Usuario> ususarioOption = usuarioRepository.findById(idUsuario);

        if(!ususarioOption.isPresent())
            throw new ObjetoNoEncontradoException(String.format("No existe un usuario con el id %s", idUsuario));

        Usuario usuarioDb = ususarioOption.get();

        if(usuarioDb.getCorreo().equals(usuario.getCorreo()))
            throw new ErrorGeneralExcepcion("No se le puede cambiar el correo al usuario");

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        if(existeCorreo(usuario.getCorreo()))
            throw new ObjetoNoEncontradoException("ya existe un registro con estos valores");

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }


    @Override
    public List<Usuario> obtenerUsuariosPorFiltro(Boolean esColaborador ) {

        GenericSpecification<Usuario> genericSpecification = new GenericSpecification<>();

        if(esColaborador)
            genericSpecification.add(new SearchCriteria("colaborador", null,
                    SearchOperation.IS_NOT_NULL));
        else
            genericSpecification.add(new SearchCriteria("acudiente", null,
                    SearchOperation.IS_NOT_NULL));



        return usuarioRepository.findAll(genericSpecification);
    }

}
