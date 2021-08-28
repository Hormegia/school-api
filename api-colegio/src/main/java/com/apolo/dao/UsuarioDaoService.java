package com.apolo.dao;

import com.apolo.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UsuarioDaoService {
    private static int userCount = 3;

    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuario(1, "carlos", "asd"));
        usuarios.add(new Usuario(2, "esteban", "asd"));
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario save (Usuario usuario){
        if(usuario.getId() == null){
            usuario.setId(++userCount);
        }
        usuarios.add(usuario);

        return usuario;
    }

    public Usuario findOne(int id){
        for ( Usuario usuario: usuarios) {
            if(usuario.getId() == id)
                return usuario;
        }
        return null;
    }

    public Usuario deleteById(int id){
        Iterator<Usuario> iterator = usuarios.iterator();
        while(iterator.hasNext()){
            Usuario usuario = iterator.next();
            if(usuario.getId() == id){
                iterator.remove();
                return usuario;
            }
        }
        return null;
    }
}
