package com.apolo.service;

import com.apolo.dao.FiltroAcudienteRequest;
import com.apolo.model.Acudiente;
import com.apolo.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IAcudienteService {

    List<Acudiente> obtenerAcudientesPorFiltro (FiltroAcudienteRequest filtroAcudienteRequest);

    Acudiente asignarEstudiante (Acudiente acudiente, Estudiante estudiante);

    Optional<Acudiente> findById(Integer id);
}
