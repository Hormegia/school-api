package com.apolo.modulos.roles.repository;

import com.apolo.modulos.roles.model.Rol;
import com.apolo.modulos.usuarios.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@Table(
        uniqueConstraints=
        @UniqueConstraint(name = "UniqueUsuarioAndRol", columnNames={"usuario_id", "rol_id"})
)

@Entity
@ApiModel(description = "Rol asociado a un usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JsonIgnoreProperties(value = {"roles",  "rolesCreados"})
    private Usuario usuario;

    @ManyToOne()
    private Rol rol;

    @ManyToOne()
    @JsonIgnoreProperties(value = {"roles", "rolesCreados"})
    private Usuario usuarioCreacion;


    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public RolUsuario(){

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

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


}
