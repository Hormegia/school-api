package com.apolo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("matriculas")
    private Usuario usuario;

    private String estudiante;
    private int codigo;

    public Matricula() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Matricula(String estudiante, int codigo) {
        this.estudiante = estudiante;
        this.codigo = codigo;
    }

    public int getCodigo() {

        return codigo;
    }

    public void setCodigo(int codigo) {

        this.codigo = codigo;
    }

    public String getEstudiante() {

        return estudiante;
    }

    public void setEstudiante(String estudiante) {

        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Matricula{");
        sb.append("id=").append(id);
        sb.append(", estudiante='").append(estudiante).append('\'');
        sb.append(", codigo=").append(codigo);
        sb.append('}');
        return sb.toString();
    }
}
