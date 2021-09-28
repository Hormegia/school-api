package com.apolo.modulos.grados.resource;

import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.grados.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class GradoJPAResource {

    private final GradoRepository gradoRepository;

    @Autowired
    public GradoJPAResource(GradoRepository gradoRepository) {
        this.gradoRepository = gradoRepository;
    }

    @GetMapping("/grados")
    public List<Grado> getAllGrados() {
        return gradoRepository.findAllByOrderByIdAsc();
    }

}
