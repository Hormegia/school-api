package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.InformacionEducativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionEducativaRepository extends JpaRepository<InformacionEducativa, Long> {
}
