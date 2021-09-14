package com.apolo.spring.autenticar.jwt;

import com.apolo.spring.util.JwtUtil;
import com.apolo.spring.model.AuthenticationRequest;
import com.apolo.spring.model.AuthenticationResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwTokenUtil;


    @PostMapping("/autenticar")
    public ResponseEntity<?> crearTokenAutenticado(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());


        final String jwt = jwTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    //se utiliza para reemplazar un token
    @GetMapping("/autenticar/token")
    public ResponseEntity<?> reemplazarTokenAutenticado( ) throws Exception {

        String nombre = SecurityContextHolder.getContext().getAuthentication().getName();
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(nombre);

        final String jwt = jwTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}