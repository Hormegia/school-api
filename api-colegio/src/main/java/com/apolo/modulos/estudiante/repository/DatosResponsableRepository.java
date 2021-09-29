package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.DatosResponsable;
import com.apolo.modulos.estudiante.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosResponsableRepository extends JpaRepository<DatosResponsable, Long> {
}
