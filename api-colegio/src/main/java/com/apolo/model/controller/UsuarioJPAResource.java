package com.apolo.model.controller;


//import com.apolo.model.repository.UsuarioDaoService;

import com.apolo.exception.UsuarioNoEncontradoException;
import com.apolo.model.Matricula;
import com.apolo.model.Usuario;
import com.apolo.model.repository.MatriculaRepository;
import com.apolo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioJPAResource {


    private final UserRepository userRepository;

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public UsuarioJPAResource(UserRepository userRepository, MatriculaRepository matriculaRepository) {
        this.userRepository = userRepository;
        this.matriculaRepository = matriculaRepository;
    }


    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios")
    public List<Usuario> retrieveAllUsuarios(){
        return userRepository.findAll();
    }

    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios/{id}")
    public EntityModel<Usuario> retrieveUsuario(@PathVariable int id){
        Optional<Usuario> ususario = userRepository.findById(id);
        if(!ususario.isPresent())
            throw new UsuarioNoEncontradoException("id-"+id);


        //HATEOAS
        EntityModel<Usuario> resource = EntityModel.of(ususario.get());

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsuarios());

        resource.add(linkTo.withRel("todos-us"));


        return resource;
    }

    //eliminar usuario
    //   /usuario/
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable int id){
        userRepository.deleteById(id);
    }

    //Crear usuario
    // Pos  /usuario/
    @PostMapping("/usuarios")
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuario usuario){
        Usuario usuarioNevo = userRepository.save(usuario);

        // /usuario/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                usuarioNevo.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/usuarios/{id}/matriculas")
    public List<Matricula> encontrasMatriculas(@PathVariable int id){
        Optional<Usuario> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UsuarioNoEncontradoException("id-" + id);
        }
        return userOptional.get().getMatriculas();
    }

    @PostMapping("/usuarios/{id}/matriculas")
    public ResponseEntity<Object> crearMatricula(@PathVariable int id, @RequestBody Matricula matricula){
        Optional<Usuario> usuarioOptional = userRepository.findById(id);
        if(!usuarioOptional.isPresent()){
            throw new UsuarioNoEncontradoException("id-" + id);
        }
        Usuario usuario = usuarioOptional.get();

        matricula.setUsuario(usuario);
        matriculaRepository.save(matricula);

        // /usuario/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        matricula.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
