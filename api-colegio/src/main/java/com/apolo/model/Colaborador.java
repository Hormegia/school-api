package com.apolo.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Table(
        uniqueConstraints=
        @UniqueConstraint(name = "UniqueDocumentoAndTipoDocumento", columnNames={"tipoDocumento", "documento"})
)

@Entity
@ApiModel(description = "Colaborador Interno")
public class Colaborador extends Persona{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Colaborador() {
        super();
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
