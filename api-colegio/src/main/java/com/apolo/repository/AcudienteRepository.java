package com.apolo.repository;

import com.apolo.model.Acudiente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcudienteRepository extends JpaRepository<Acudiente, Integer>, JpaSpecificationExecutor<Acudiente> {
    
}
