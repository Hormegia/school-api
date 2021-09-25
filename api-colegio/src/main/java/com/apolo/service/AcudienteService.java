package com.apolo.service;

import com.apolo.dao.FiltroAcudienteRequest;
import com.apolo.model.Acudiente;
import com.apolo.model.Estudiante;
import com.apolo.repository.AcudienteRepository;
import com.apolo.spring.database.GenericSpesification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        GenericSpesification<Acudiente> genericSpesification = new GenericSpesification<>();


        if(filtro.getPrimerNombre() != null)
            genericSpesification.add(new SearchCriteria("primerNombre", filtro.getPrimerNombre(),
                    SearchOperation.EQUAL));

        if(filtro.getPrimerApellido() != null)
            genericSpesification.add(new SearchCriteria("primerApellido", filtro.getPrimerApellido(),
                    SearchOperation.EQUAL));

        if(filtro.getTipoDocumento() != null && filtro.getDocumento() != null){
            genericSpesification.add(new SearchCriteria("documento", filtro.getDocumento(),
                    SearchOperation.EQUAL));

        }

        if(filtro.getNumeroCelular() != null)
            genericSpesification.add(new SearchCriteria("numeroCelular", filtro.getNumeroCelular(),
                    SearchOperation.EQUAL));

        return acudienteRepository.findAll(genericSpesification);
    }

    @Override
    public Acudiente asignarEstudiante(Estudiante estudiante) {
        return null;
    }
}
