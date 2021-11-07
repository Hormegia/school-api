package com.apolo.modulos.estudiante.repository;

import com.apolo.modulos.estudiante.model.InformacionEducativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacionEducativaRepository extends JpaRepository<InformacionEducativa, Long> {

    List<InformacionEducativa> getByMatriculaIdOrderByFechaAsc(Long idMatricula);
}
