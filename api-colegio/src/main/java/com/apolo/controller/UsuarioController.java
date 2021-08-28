package com.apolo.controller;


import com.apolo.model.Usuario;
import com.apolo.dao.UsuarioDaoService;
import com.apolo.exception.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UsuarioController {


    private final UsuarioDaoService servicio;

    @Autowired
    public UsuarioController(UsuarioDaoService servicio) {
        this.servicio = servicio;
    }


    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios")
    public List<Usuario> retrieveAllUsuarios(){
        return servicio.findAll();
    }

    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios/{id}")
    public EntityModel<Usuario> retrieveUsuario(@PathVariable int id){
        Usuario ususario = servicio.findOne(id);
        if(ususario == null)
            throw new UsuarioNoEncontradoException("id-"+id);


        //HATEOAS
        EntityModel<Usuario> resource = EntityModel.of(ususario);

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsuarios());

        resource.add(linkTo.withRel("todos-us"));


        return resource;
    }

    //eliminar usuario
    //   /usuario/
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable int id){
        Usuario ususario = servicio.deleteById(id);
        if(ususario == null)
            throw new UsuarioNoEncontradoException("id-"+id);
    }

    //Crear usuario
    // Pos  /usuario/
    @PostMapping("/usuarios")
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuario usuario){
        Usuario usuarioNUevo = servicio.save(usuario);

        // /usuario/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                usuarioNUevo.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // un usuario espeifico
}
