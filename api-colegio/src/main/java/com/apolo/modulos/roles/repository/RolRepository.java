package com.apolo.modulos.roles.repository;

import com.apolo.modulos.roles.enums.Credencial;
import com.apolo.modulos.roles.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByCredencial(Credencial credential);
}
