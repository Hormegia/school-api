package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;

public interface IMatriculaService {

    Matricula crearMatriculaEstudiante(MatriculaEstudianteRequest matriculaEstudianteRequest);
}
