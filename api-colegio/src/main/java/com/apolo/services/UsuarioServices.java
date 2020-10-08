package com.apolo.services;

import com.apolo.model.UsuarioEntity;
import com.apolo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    UsuarioRepository repository;

    public UsuarioEntity getEmployeeById(Integer id) throws Exception
    {
        Optional<UsuarioEntity> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public UsuarioEntity createOrUpdateEmployee(UsuarioEntity entity) throws Exception
    {
        Optional<UsuarioEntity> employee = repository.findById(entity.getId());


            entity = repository.save(entity);

            return entity;

    }
}
