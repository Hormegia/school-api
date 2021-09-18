package com.apolo.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@ApiModel(description = "detalles usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=2, message = "minimo 2")
    private String nombre;

    //ToDo mejorar expresión
    @NotNull
    @Pattern(regexp="^(.+)@(.+)$",
            message = "no cumple con la estructura de un correo")

    @NotNull
    private String correo;

    private Boolean habilitado;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private String password;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<RolUsuario> roles;

    @OneToMany(mappedBy = "usuarioCreacion")
    @JsonIgnoreProperties("usuarioCreacion")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY )
    private List<RolUsuario> rolesCreados;



    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public Usuario() {
        this.habilitado = false;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Usuario(Integer id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolUsuario> getRoles() {
        return roles;
    }

    public void setRoles(List<RolUsuario> roles) {
        this.roles = roles;
    }

    public List<RolUsuario> getRolesCreados() {
        return rolesCreados;
    }

    public void setRolesCreados(List<RolUsuario> rolesCreados) {
        this.rolesCreados = rolesCreados;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", correo='").append(correo).append('\'');
        sb.append(", matriculas=").append(matriculas);
        sb.append('}');
        return sb.toString();
    }
}
