package com.apolo.modulos.colaborador.model;

import com.apolo.modulos.usuarios.model.Persona;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Table(
        uniqueConstraints=
        @UniqueConstraint(name = "UniqueDocumentoAndTipoDocumento", columnNames={"tipoDocumento", "documento"})
)

@Entity
@ApiModel(description = "Colaborador Interno")
public class Colaborador extends Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Colaborador() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
