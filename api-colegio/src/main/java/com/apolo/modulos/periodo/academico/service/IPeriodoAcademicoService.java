package com.apolo.modulos.periodo.academico.service;

import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;

import java.util.List;
import java.util.Optional;

public interface IPeriodoAcademicoService {

    List<PeriodoAcademico> obtenerPeriodosSobrelapados(PeriodoAcademico periodoAcademico);

    Optional<PeriodoAcademico> obtenerPeriodoAnterior(PeriodoAcademico periodoAcademico);
}
