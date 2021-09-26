package com.apolo.service;

import com.apolo.dao.FiltroAcudienteRequest;
import com.apolo.model.Acudiente;
import com.apolo.model.Estudiante;
import com.apolo.repository.AcudienteRepository;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class AcudienteService implements IAcudienteService{

    private final AcudienteRepository acudienteRepository;

    @Autowired
    public AcudienteService(AcudienteRepository acudienteRepository) {
        this.acudienteRepository = acudienteRepository;
    }



    @Override
    public List<Acudiente> obtenerAcudientesPorFiltro(FiltroAcudienteRequest filtro) {

        GenericSpecification<Acudiente> genericSpecification = new GenericSpecification<>();


        if(filtro.getPrimerNombre() != null)
            genericSpecification.add(new SearchCriteria("primerNombre", filtro.getPrimerNombre(),
                    SearchOperation.EQUAL));

        if(filtro.getPrimerApellido() != null)
            genericSpecification.add(new SearchCriteria("primerApellido", filtro.getPrimerApellido(),
                    SearchOperation.EQUAL));

        if(filtro.getTipoDocumento() != null && filtro.getDocumento() != null){
            genericSpecification.add(new SearchCriteria("documento", filtro.getDocumento(),
                    SearchOperation.EQUAL));

        }

        if(filtro.getNumeroCelular() != null)
            genericSpecification.add(new SearchCriteria("numeroCelular", filtro.getNumeroCelular(),
                    SearchOperation.EQUAL));

        return acudienteRepository.findAll(genericSpecification);
    }

    @Override
    public Acudiente asignarEstudiante(Estudiante estudiante) {
        return null;
    }
}
