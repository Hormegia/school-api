package com.apolo.service;

import com.apolo.dao.FiltroEstudianteRequest;
import com.apolo.model.Estudiante;
import com.apolo.repository.EstudianteRepository;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteService implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    @Override
    public List<Estudiante> obtenerEstudiantesPorFiltro(FiltroEstudianteRequest filtro) {
        GenericSpecification<Estudiante> genericSpecification = new GenericSpecification<>();

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
        return estudianteRepository.findAll(genericSpecification);
    }

    @Override
    public Optional<Estudiante> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        estudianteRepository.deleteById(id);
    }
}
