package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.DatosResponsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosResponsableRepository extends JpaRepository<DatosResponsable, Long>, JpaSpecificationExecutor<DatosResponsable> {

    DatosResponsable findByEstudianteIdAndEsAcudiente(Long idEstudiante, Boolean esAcudiente);

    DatosResponsable findByEstudianteIdAndEsPadreAndEsAcudienteFalse(Long idEstudiante, Boolean esPadre);


}
