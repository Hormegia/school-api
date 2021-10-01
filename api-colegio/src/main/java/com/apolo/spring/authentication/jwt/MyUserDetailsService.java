package com.apolo.spring.authentication.jwt;

import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.modulos.roles.model.RolUsuario;
import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MyUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {



        Optional<Usuario> usuario = usuarioRepository.findUsuarioByCorreo(nombreUsuario);

        if (!usuario.isPresent()) {
            throw new ObjetoNoEncontradoException("Usuario no encontrado");
        }

        boolean enabled = usuario.get().getHabilitado();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                usuario.get().getCorreo(), usuario.get().getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(usuario.get().getRoles()));

    }

    private static List<GrantedAuthority> getAuthorities(List<RolUsuario>roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RolUsuario rol : roles) {
            authorities.add(new SimpleGrantedAuthority(rol.getRol().getCredencial().getCodigo()));
        }
        return authorities;

    }

}
