package com.apolo.modulos.periodo.academico.repository;

import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Integer>{
}
