package com.apolo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "colegio_corral", catalog = "")
public class UsuarioEntity {
    private int id;
    private AdministrativoEntity administrativoByAdministrativoId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "administrativo_id", referencedColumnName = "id", nullable = false)
    public AdministrativoEntity getAdministrativoByAdministrativoId() {
        return administrativoByAdministrativoId;
    }

    public void setAdministrativoByAdministrativoId(AdministrativoEntity administrativoByAdministrativoId) {
        this.administrativoByAdministrativoId = administrativoByAdministrativoId;
    }
}
