package com.apolo.resource;


//import com.apolo.repository.UsuarioDaoService;

import com.apolo.model.Rol;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.model.Matricula;
import com.apolo.model.TokenActivacionUsuario;
import com.apolo.model.Usuario;
import com.apolo.event.onRegistroUsuarioEvent;
import com.apolo.repository.MatriculaRepository;
import com.apolo.repository.UserRepository;
import com.apolo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class UsuarioJPAResource {


    private final IUsuarioService iUsuarioService;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public UsuarioJPAResource(IUsuarioService iUsuarioService, UserRepository userRepository, MatriculaRepository matriculaRepository, ApplicationEventPublisher eventPublisher) {
        this.iUsuarioService = iUsuarioService;
        this.userRepository = userRepository;
        this.matriculaRepository = matriculaRepository;
        this.eventPublisher = eventPublisher;
    }


    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios")
    public List<Usuario> retrieveAllUsuarios() {
        return userRepository.findAll();
    }

    //Trae un usuario por id
    // GET  /usuarios/id
    @GetMapping("/usuarios/{id}")
    public EntityModel<Usuario> retrieveUsuario(@PathVariable int id) {

        Optional<Usuario> ususario = userRepository.findById(id);
        if (!ususario.isPresent())
            throw new ObjetoNoEncontradoException("id-" + id);

        //HATEOAS
        EntityModel<Usuario> resource = EntityModel.of(ususario.get());

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsuarios());

        resource.add(linkTo.withRel("obtener-todos"));


        return resource;
    }


    //eliminar usuario
    //usuarios/id
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable int id) {
        userRepository.deleteById(id);
    }


    @PostMapping("/usuarios/crear")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {


        String appUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/usuarios/activar").toUriString();
        Locale locale = LocaleContextHolder.getLocale();


        Usuario usuarioNevo = iUsuarioService.registrarUsuario(usuario);
        //se lanza evento para enviar correo con el lik de activación
        eventPublisher.publishEvent(new onRegistroUsuarioEvent(usuarioNevo,
                appUrl, locale));

        /* Responde en el header el lugar donde se puede encontrar la información
         * del usuario
         */
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/usuarios/{id}")
                .buildAndExpand(
                        usuarioNevo.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    //Actualizar Usuario
    //Se maneja otro para crear usuarios porque es necesario la autenticación
    // Post  /usuarios/
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {

        Usuario usuarioActualizado = iUsuarioService.actualizarUsuario(usuario);

        /* Responde en el header el lugar donde se puede encontrar la información
         * del usuario
         */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        usuarioActualizado.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/usuarios/{id}/matriculas")
    public List<Matricula> encontrasMatriculas(@PathVariable int id) {
        Optional<Usuario> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ObjetoNoEncontradoException("id-" + id);
        }
        return userOptional.get().getMatriculas();
    }

    @GetMapping("/usuarios/activar/{token}")
    public String activarUsuario(@PathVariable String token) {
        TokenActivacionUsuario tokenActivacionUsuario = iUsuarioService.getTokenActivacion(token);
        if (tokenActivacionUsuario == null)
            throw new ObjetoNoEncontradoException("Token Expirado");

        Usuario usuario = iUsuarioService.getUsuario(token);

        usuario.setHabilitado(true);
        userRepository.save(usuario);

        return "usuario habilitado";
    }

    @PostMapping("/usuarios/roles/")
    public ResponseEntity<Usuario> crearRolUsuario(@Valid @RequestBody Usuario usuario ) {

//        Optional<Usuario> usuarioOptional = userRepository.findById(id);
//
//        if(!usuarioOptional.isPresent()){
//            throw new ObjetoNoEncontradoException("id-" + id);
//        }
//        Usuario usuario = usuarioOptional.get();
//
//        matricula.setUsuario(usuario);
//        matriculaRepository.save(matricula);

        // /usuario/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        1).toUri();
        System.out.println(ResponseEntity.created(location).build());

        return ResponseEntity.created(location).build();
    }
}
