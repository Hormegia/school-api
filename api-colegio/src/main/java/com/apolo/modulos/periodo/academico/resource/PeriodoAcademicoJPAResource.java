package com.apolo.modulos.periodo.academico.resource;

import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.apolo.modulos.periodo.academico.repository.PeriodoAcademicoRepository;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class PeriodoAcademicoJPAResource {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;


    @Autowired
    public PeriodoAcademicoJPAResource(PeriodoAcademicoRepository periodoAcademicoRepository) {
        this.periodoAcademicoRepository = periodoAcademicoRepository;
    }
    //todos los periodos
    // GET  /periodo/
    @GetMapping("/periodos")
    public List<PeriodoAcademico> getAll() {
        return periodoAcademicoRepository.findAll();
    }


    //Trae un periodo por id
    // GET  /periodos/id
    @GetMapping("/periodos/{id}")
    public EntityModel<PeriodoAcademico> getById(@PathVariable int id) {

        Optional<PeriodoAcademico> periodo = periodoAcademicoRepository.findById(id);
        if (!periodo.isPresent())
            throw new ObjetoNoEncontradoException("No se encontro el periodo con el id: " + id);

        //HATEOAS
        EntityModel<PeriodoAcademico> resource = EntityModel.of(periodo.get());

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll());

        resource.add(linkTo.withRel("obtener-todos"));


        return resource;
    }
    //eliminar periodo
    //periodo/id
    @DeleteMapping("/periodos/{id}")
    public void deleteById(@PathVariable int id) {
        periodoAcademicoRepository.deleteById(id);
    }

    //crear periodo
    @PostMapping("/periodos")
    public EntityModel<PeriodoAcademico> creaOEditarPeriodo(@Valid @RequestBody PeriodoAcademico periodoAcademico) {
        Integer idPeriodo = periodoAcademico.getId();

        if (idPeriodo != null) {
            Optional<PeriodoAcademico> periodoExistente = periodoAcademicoRepository.findById(idPeriodo);
            if (!periodoExistente.isPresent())
                throw new ObjetoNoEncontradoException("No se encontro el periodo con el id: " + idPeriodo);
        }else{
            periodoAcademico.setFechaCreacionPeriodo(new Date());
        }

        PeriodoAcademico nuevoPeriodoAcademico = periodoAcademicoRepository.save(periodoAcademico);

        return EntityModel.of(nuevoPeriodoAcademico);

}

}
