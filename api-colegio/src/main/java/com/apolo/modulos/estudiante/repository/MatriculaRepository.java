package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long>, JpaSpecificationExecutor<Matricula> {


}
