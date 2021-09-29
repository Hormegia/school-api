package com.apolo.modulos.estudiante.service;


import com.apolo.modulos.estudiante.dao.FiltroEstudianteRequest;
import com.apolo.modulos.estudiante.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {

    List<Estudiante> obtenerEstudiantesPorFiltro (FiltroEstudianteRequest filtroEstudianteRequest);

    Optional<Estudiante> findById(Long id);

    Optional<Estudiante> crearOEditarEstudiante (Estudiante estudiante);

    void deleteById (Long id);
}

