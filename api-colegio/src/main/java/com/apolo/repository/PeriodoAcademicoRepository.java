package com.apolo.repository;

import com.apolo.model.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Integer>{
}
