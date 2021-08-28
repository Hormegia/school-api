package com.apolo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "formulario_matricula", schema = "colegio_corral", catalog = "")
public class FormularioMatriculaEntity {
    private int periodoAcademicoId;

    @Id
    @Column(name = "periodo_academico_id", nullable = false)
    public int getPeriodoAcademicoId() {
        return periodoAcademicoId;
    }

    public void setPeriodoAcademicoId(int periodoAcademicoId) {
        this.periodoAcademicoId = periodoAcademicoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioMatriculaEntity that = (FormularioMatriculaEntity) o;
        return periodoAcademicoId == that.periodoAcademicoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodoAcademicoId);
    }
}
