package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>, JpaSpecificationExecutor<Estudiante> {
}
