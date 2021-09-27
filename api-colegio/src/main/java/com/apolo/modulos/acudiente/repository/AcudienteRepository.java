package com.apolo.modulos.acudiente.repository;

import com.apolo.modulos.acudiente.model.Acudiente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcudienteRepository extends JpaRepository<Acudiente, Integer>, JpaSpecificationExecutor<Acudiente> {
    
}
