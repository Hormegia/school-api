package com.apolo.model.controller;


import com.apolo.model.Matricula;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MatriculaController {

    @GetMapping("/matricula")
    public List<Matricula> getAll() {
        return Arrays.asList(new Matricula("wqeasf", 45));
    }

    @GetMapping("/matricula-bean")
    public Matricula getAllBean() {
        return new Matricula("wqeasf", 45);
    }

    @GetMapping("/matricula-bean/{id}")
    public Matricula getAllBeanPathVariable(@PathVariable int id) {
        return new Matricula("wqeasf", 45);
    }
}

