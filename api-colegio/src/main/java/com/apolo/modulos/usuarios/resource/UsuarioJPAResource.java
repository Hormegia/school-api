package com.apolo.modulos.usuarios.resource;


//import com.apolo.repository.UsuarioDaoService;

import com.apolo.dao.DeleteResponse;
import com.apolo.dao.GenericResponse;
import com.apolo.modulos.usuarios.dao.FiltroUsuarioRequest;
import com.apolo.modulos.usuarios.dao.UsuarioAcudienteRequest;
import com.apolo.modulos.usuarios.dao.UsuarioColaboradorRequest;
import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.colaborador.model.Colaborador;
import com.apolo.modulos.roles.repository.RolUsuario;
import com.apolo.modulos.usuarios.model.TokenActivacionUsuario;
import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.acudiente.repository.AcudienteRepository;
import com.apolo.modulos.colaborador.repository.ColaboradorRepository;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.apolo.modulos.usuarios.event.onRegistroUsuarioEvent;
import com.apolo.modulos.usuarios.repository.UsuarioRepository;
import com.apolo.modulos.usuarios.service.IUsuarioService;
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
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class UsuarioJPAResource {


    private final IUsuarioService iUsuarioService;

    private final UsuarioRepository usuarioRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final AcudienteRepository acudienteRepository;

    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public UsuarioJPAResource(IUsuarioService iUsuarioService, UsuarioRepository usuarioRepository, ApplicationEventPublisher eventPublisher, AcudienteRepository acudienteRepository, ColaboradorRepository colaboradorRepository) {
        this.iUsuarioService = iUsuarioService;
        this.usuarioRepository = usuarioRepository;
        this.eventPublisher = eventPublisher;
        this.acudienteRepository = acudienteRepository;
        this.colaboradorRepository = colaboradorRepository;
    }


    //todos los usuarios
    // GET  /usuario/
    @GetMapping("/usuarios")
    public List<Usuario> getAll(@RequestParam(required = false) Boolean esColaborador) {
        return iUsuarioService.obtenerUsuariosPorFiltro(esColaborador);
    }

    //Trae un usuario por id
    // GET  /usuarios/id
    @GetMapping("/usuarios/{id}")
    public EntityModel<Usuario> getById(@PathVariable Long id) {

        Optional<Usuario> ususario = usuarioRepository.findById(id);
        if (!ususario.isPresent())
            throw new ObjetoNoEncontradoException("No se existe un usuario con id: " + id);

        //HATEOAS
        EntityModel<Usuario> resource = EntityModel.of(ususario.get());

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll(null));

        resource.add(linkTo.withRel("obtener-todos"));


        return resource;
    }


    //eliminar usuario
    //usuarios/id
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        usuarioRepository.deleteById(id);

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
    public EntityModel<Usuario>  crearUsuarioColaborador(@Valid @RequestBody UsuarioColaboradorRequest usuarioColaboradorRequest) {

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

        return EntityModel.of(usuarioNevo);
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
        usuarioRepository.save(usuario);

        GenericResponse response = new GenericResponse();
        response.setMensaje("El usuario ha sido habilitado");

        return response;
    }

    @PostMapping("/usuarios/{id}/roles")
    public EntityModel<RolUsuario> crearRolUsuario(@Valid @RequestBody RolUsuario rolUsuario, @PathVariable Long id) {

        RolUsuario nuevoRolUsuario = iUsuarioService.agregarRolUsuario(rolUsuario, id);


        return EntityModel.of(nuevoRolUsuario);
    }

    @DeleteMapping("/usuarios/{id}/roles")
    public ResponseEntity<?> eliminarRolUsuario(@Valid @RequestBody RolUsuario rolUsuario, @PathVariable Long id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        iUsuarioService.eliminarRolUsuario(rolUsuario, id);

        return ResponseEntity.ok(resource);
    }
}
