package com.apolo.modulos.estudiante.resource;


import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.usuarios.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class MatriculaJPAResource {

    private final MatriculaRepository matriculaRepository;

    public MatriculaJPAResource(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    //crear matricula
    // GET  /usuario/
    @GetMapping("/matriculas")
    public List<Matricula> getAll() {
        return matriculaRepository.findAll();
    }
}

