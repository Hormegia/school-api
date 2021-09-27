package com.apolo.resource;

import com.apolo.dao.FiltroAcudienteRequest;
import com.apolo.model.Acudiente;
import com.apolo.model.Estudiante;
import com.apolo.model.Usuario;
import com.apolo.service.IAcudienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class AcudienteJPAResource {

    private final IAcudienteService acudienteService;


    @Autowired
    public AcudienteJPAResource(IAcudienteService acudienteService) {
        this.acudienteService = acudienteService;

    }


    @GetMapping("/acudientes")
    public List<Acudiente> getByFilter(@RequestBody FiltroAcudienteRequest filtroAcudienteRequest) {

        return acudienteService.obtenerAcudientesPorFiltro(filtroAcudienteRequest);
    }

    @GetMapping("/acudientes/{id}")
    public EntityModel<Acudiente> getById(@PathVariable int id) {

        Optional<Acudiente> acudiente = acudienteService.findById(id);

        return EntityModel.of(acudiente.get());
    }

    @PostMapping("/acudientes/{id}/estudiantes")
    public List<Estudiante> asignarEstudiante(@Valid @RequestBody Estudiante estudiante, @PathVariable int id ){

        Acudiente acudiente = acudienteService.findById(id).get();

        acudiente = acudienteService.asignarEstudiante(acudiente, estudiante);


        return acudiente.getEstudiante();
    }


    //retorna todos los estudiantes de un usuario
    @PostMapping("/acudientes/{id}/estudiantes")
    public List<Estudiante> getEstudiantes(@PathVariable int id ){

        Acudiente acudiente = acudienteService.findById(id).get();

        return acudiente.getEstudiante();
    }
}
