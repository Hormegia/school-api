package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.InformacionAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionAdicionalRepository extends JpaRepository<InformacionAdicional, Long> {
}
