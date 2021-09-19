package com.apolo.spring.autenticar.jwt;

import com.apolo.model.Usuario;
import com.apolo.repository.UserRepository;
import com.apolo.resource.UsuarioJPAResource;
import com.apolo.spring.util.JwtUtil;
import com.apolo.spring.dao.AuthenticationRequest;
import com.apolo.spring.dao.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/autenticar")
    public ResponseEntity<?> crearTokenAutenticado(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getCorreo(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getCorreo());


        return getResponseEntity(userDetails);
    }

    //se utiliza para reemplazar un token
    @GetMapping("/autenticar/token")
    public ResponseEntity<?> reemplazarTokenAutenticado( ) throws Exception {

        String nombre = SecurityContextHolder.getContext().getAuthentication().getName();

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(nombre);

        return getResponseEntity(userDetails);
    }


    /**
     * Se agrega HATEOAS con información del usuario al jwt
     */
    private ResponseEntity<?> getResponseEntity(UserDetails userDetails) {
        final String jwt = jwTokenUtil.generateToken(userDetails);

        Optional<Usuario> usuario = userRepository.findUsuarioByCorreo(userDetails.getUsername());

        EntityModel<?> resource = EntityModel.of(new AuthenticationResponse(jwt));

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioJPAResource.class).retrieveUsuario(usuario.get().getId()));

        resource.add(linkTo.withRel("usuario"));

        return ResponseEntity.ok(resource);
    }
}