package com.apolo.modulos.acudiente.model;

import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.usuarios.model.Persona;
import com.apolo.modulos.usuarios.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@Table(
        uniqueConstraints=
        @UniqueConstraint(name = "UniqueDocumentoAndTipoDocumento", columnNames={"tipoDocumento", "documento"})
)

@Entity
@ApiModel(description = "Estudiante registrado")
public class Acudiente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "acudiente")
    @JsonIgnoreProperties(value = {"acudiente"})
    private Usuario usuario;

    @OneToMany(mappedBy = "acudiente")
    @JsonIgnoreProperties(value = {"acudiente"})
    private List<Estudiante> estudiante;


    public Acudiente() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }
}
