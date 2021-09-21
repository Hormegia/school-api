package com.apolo.resource;


//import com.apolo.repository.UsuarioDaoService;

import com.apolo.dao.DeleteResponse;
import com.apolo.dao.GenericResponse;
import com.apolo.dao.UsuarioAcudienteRequest;
import com.apolo.dao.UsuarioColaboradorRequest;
import com.apolo.model.*;
import com.apolo.repository.AcudienteRepository;
import com.apolo.repository.ColaboradorRepository;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.event.onRegistroUsuarioEvent;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class UsuarioJPAResource {


    private final IUsuarioService iUsuarioService;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final AcudienteRepository acudienteRepository;

    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public UsuarioJPAResource(IUsuarioService iUsuarioService, UserRepository userRepository, ApplicationEventPublisher eventPublisher, AcudienteRepository acudienteRepository, ColaboradorRepository colaboradorRepository) {
        this.iUsuarioService = iUsuarioService;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.acudienteRepository = acudienteRepository;
        this.colaboradorRepository = colaboradorRepository;
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
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        userRepository.deleteById(id);

        return ResponseEntity.ok(resource);
    }


    @PostMapping("/usuarios/acudientes/crear")
    public ResponseEntity<Usuario> crearUsuarioAcudiente(@Valid @RequestBody UsuarioAcudienteRequest usuarioAcudienteRequest) {

        Usuario usuario = usuarioAcudienteRequest.getUsuario();


        Acudiente acudiente = usuarioAcudienteRequest.getAcudiente();

        usuario.setAcudiente(acudiente);

        acudienteRepository.save(acudiente);

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


    @PostMapping("/usuarios/colaboradores/crear")
    public ResponseEntity<Usuario> crearUsuarioColaborador(@Valid @RequestBody UsuarioColaboradorRequest usuarioColaboradorRequest) {

        Usuario usuario = usuarioColaboradorRequest.getUsuario();


        Colaborador colaborador = usuarioColaboradorRequest.getColaborador();

        usuario.setColaborador(colaborador);

        colaboradorRepository.save(colaborador);

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
    public EntityModel<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {

        Usuario usuarioActualizado = iUsuarioService.actualizarUsuario(usuario);

        return EntityModel.of(usuarioActualizado);
    }

    @GetMapping("/usuarios/activar/{token}")
    public GenericResponse activarUsuario(@PathVariable String token) {
        TokenActivacionUsuario tokenActivacionUsuario = iUsuarioService.getTokenActivacion(token);
        if (tokenActivacionUsuario == null)
            throw new ObjetoNoEncontradoException("Token Expirado");

        Usuario usuario = iUsuarioService.getUsuario(token);

        usuario.setHabilitado(true);
        userRepository.save(usuario);

        GenericResponse response = new GenericResponse();
        response.setMensaje("El usuario ha sido habilitado");

        return response;
    }

    @PostMapping("/usuarios/{id}/roles")
    public EntityModel<RolUsuario> crearRolUsuario(@Valid @RequestBody RolUsuario rolUsuario, @PathVariable int id) {

        RolUsuario nuevoRolUsuario = iUsuarioService.agregarRolUsuario(rolUsuario, id);


        return EntityModel.of(nuevoRolUsuario);
    }

    @DeleteMapping("/usuarios/{id}/roles")
    public ResponseEntity<?> eliminarRolUsuario(@Valid @RequestBody RolUsuario rolUsuario, @PathVariable int id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        iUsuarioService.eliminarRolUsuario(rolUsuario, id);

        return ResponseEntity.ok(resource);
    }
}
