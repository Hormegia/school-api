package com.apolo.modulos.periodo.academico.service;


import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico_;
import com.apolo.modulos.periodo.academico.repository.PeriodoAcademicoRepository;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeriodoAcademicoService implements IPeriodoAcademicoService{

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    @Autowired
    public PeriodoAcademicoService(PeriodoAcademicoRepository periodoAcademicoRepository) {
        this.periodoAcademicoRepository = periodoAcademicoRepository;
    }

    @Override
    public List<PeriodoAcademico> obtenerPeriodosSobrelapados(PeriodoAcademico periodoAcademico) {

        GenericSpecification<PeriodoAcademico> genericSpecification = new GenericSpecification<>();

        if(periodoAcademico.getId() != null)
            genericSpecification.add(new SearchCriteria(PeriodoAcademico_.ID, periodoAcademico.getId(),
                    SearchOperation.NOT_EQUAL));

        genericSpecification.add(new SearchCriteria(PeriodoAcademico_.FECHA_INICIO, periodoAcademico.getFechaFin(),
                SearchOperation.LESS_THAN, periodoAcademico.getFechaFin()));

        genericSpecification.add(new SearchCriteria(PeriodoAcademico_.FECHA_FIN, periodoAcademico.getFechaInicio(),
                SearchOperation.GREATER_THAN, periodoAcademico.getFechaInicio()));


        return periodoAcademicoRepository.findAll(genericSpecification);
    }

    @Override
    public Optional<PeriodoAcademico> obtenerPeriodoAnterior(PeriodoAcademico periodoAcademico) {

        GenericSpecification<PeriodoAcademico> genericSpecification = new GenericSpecification<>();


        genericSpecification.add(new SearchCriteria(PeriodoAcademico_.FECHA_FIN, periodoAcademico.getFechaInicio(),
                SearchOperation.LESS_THAN, periodoAcademico.getFechaInicio()));

        genericSpecification.add(new SearchCriteria(PeriodoAcademico_.FECHA_INICIO, periodoAcademico.getFechaInicioInscripcion1(),
                SearchOperation.LESS_THAN, periodoAcademico.getFechaInicioInscripcion1()));

        genericSpecification.add(new SearchCriteria(PeriodoAcademico_.FECHA_FIN, periodoAcademico.getFechaFinInscripcion1(),
                SearchOperation.GREATER_THAN, periodoAcademico.getFechaFinInscripcion1()));

        return periodoAcademicoRepository.findOne(genericSpecification);
    }


}
