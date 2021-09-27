package com.apolo.repository;

import com.apolo.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Integer> {
    public List<Grado> findAllByOrderByIdAsc();
}
