package com.apolo.model.repository;

import com.apolo.model.Matricula;
import com.apolo.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
