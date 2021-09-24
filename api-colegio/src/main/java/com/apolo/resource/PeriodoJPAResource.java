package com.apolo.resource;

import com.apolo.model.*;
import com.apolo.repository.PeriodoRepository;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class PeriodoJPAResource {

    private final PeriodoRepository periodoRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public PeriodoJPAResource(PeriodoRepository periodoRepository, ApplicationEventPublisher eventPublisher) {
        this.periodoRepository = periodoRepository;
        this.eventPublisher = eventPublisher;
    }
    //todos los periodos
    // GET  /periodo/
    @GetMapping("/periodos")
    public List<Periodo> retrieveAllPeriodo() {
        return periodoRepository.findAll();
    }
    // cual es el error?

    //Trae un periodo por id
    // GET  /periodos/id
    @GetMapping("/periodos/{id}")
    public EntityModel<Periodo> retrievePeriodo(@PathVariable int id) {

        Optional<Periodo> periodo = periodoRepository.findById(id);
        if (!periodo.isPresent())
            throw new ObjetoNoEncontradoException("id-" + id);

        //HATEOAS
        EntityModel<Periodo> resource = EntityModel.of(periodo.get());

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPeriodo());

        resource.add(linkTo.withRel("obtener-todos"));


        return resource;
    }
    //eliminar periodo
    //periodo/id
    @DeleteMapping("/periodo/{id}")
    public void deletePeriodo(@PathVariable int id) {
        periodoRepository.deleteById(id);
    }

//crear periodo
@PostMapping("/periodo")
public EntityModel<Periodo> creaOEditarPeriodo(@Valid @RequestBody Periodo periodo) {
    Integer idPeriodo = periodo.getId();

    if (idPeriodo != null) {
        Optional<Periodo> periodoExistente = periodoRepository.findById(idPeriodo);
        if (!periodoExistente.isPresent())
            throw new ObjetoNoEncontradoException("id-" + idPeriodo);
    }

    Periodo nuevoPeriodo = periodoRepository.save(periodo);

    return EntityModel.of(nuevoPeriodo);

}

}
