package com.apolo.apicolegio;


import com.apolo.model.UsuarioEntity;
import com.apolo.repositories.UsuarioRepository;
import com.apolo.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class testApi {

    @Autowired
    UsuarioServices service;
    ConcurrentHashMap<String, Persona>  data = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public Persona getTest (@PathVariable String id){
        return data.get(id);
    }

    @GetMapping("/")
    public List<Persona> getAllTest (){
        return new ArrayList<>(data.values());
    }

    @PostMapping("/")
    public ResponseEntity addContact (@RequestBody UsuarioEntity persona) throws Exception {
        UsuarioEntity updated = service.createOrUpdateEmployee(persona);
        return new ResponseEntity<UsuarioEntity>(updated, new HttpHeaders(), HttpStatus.OK);

    }

}
