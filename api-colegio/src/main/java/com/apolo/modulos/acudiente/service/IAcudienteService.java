package com.apolo.modulos.acudiente.service;

import com.apolo.modulos.acudiente.dao.FiltroAcudienteRequest;
import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.estudiante.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IAcudienteService {

    List<Acudiente> obtenerAcudientesPorFiltro (FiltroAcudienteRequest filtroAcudienteRequest);

    Acudiente asignarEstudiante (Acudiente acudiente, Estudiante estudiante);

    Optional<Acudiente> findById(Integer id);
}
