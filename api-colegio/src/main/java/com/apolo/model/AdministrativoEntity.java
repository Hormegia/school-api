package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administrativo", schema = "colegio_corral", catalog = "")
public class AdministrativoEntity {
    private int id;
    private int usuarioId;
    private int formularioAdministrativoId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "usuario_id", nullable = false)
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Basic
    @Column(name = "formulario_administrativo_id", nullable = false)
    public int getFormularioAdministrativoId() {
        return formularioAdministrativoId;
    }

    public void setFormularioAdministrativoId(int formularioAdministrativoId) {
        this.formularioAdministrativoId = formularioAdministrativoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrativoEntity that = (AdministrativoEntity) o;
        return id == that.id &&
                usuarioId == that.usuarioId &&
                formularioAdministrativoId == that.formularioAdministrativoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioId, formularioAdministrativoId);
    }
}
