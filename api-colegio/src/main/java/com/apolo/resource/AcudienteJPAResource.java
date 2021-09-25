package com.apolo.resource;

import com.apolo.dao.FiltroAcudienteRequest;
import com.apolo.model.Acudiente;
import com.apolo.repository.AcudienteRepository;
import com.apolo.service.AcudienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class AcudienteJPAResource {

    private final AcudienteService acudienteService;

    @Autowired
    public AcudienteJPAResource(AcudienteService acudienteService) {
        this.acudienteService = acudienteService;

    }


    @GetMapping("/acudientes")
    public List<Acudiente> getByFilter(@RequestBody FiltroAcudienteRequest filtroAcudienteRequest) {

        return acudienteService.obtenerAcudientesPorFiltro(filtroAcudienteRequest);
    }

}
