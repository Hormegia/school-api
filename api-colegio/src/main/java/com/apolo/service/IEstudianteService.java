package com.apolo.service;


import com.apolo.dao.FiltroEstudianteRequest;
import com.apolo.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {

    List<Estudiante> obtenerEstudiantesPorFiltro (FiltroEstudianteRequest filtroEstudianteRequest);

    Optional<Estudiante> findById(Integer id);

    void deleteById (Integer id);
}

