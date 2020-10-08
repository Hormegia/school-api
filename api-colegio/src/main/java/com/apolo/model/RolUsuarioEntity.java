package com.apolo.model;

import javax.persistence.*;

@Entity
@Table(name = "rol_usuario", schema = "colegio_corral", catalog = "")
public class RolUsuarioEntity {
    private int id;
    private RolEntity rolByRolId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
    public RolEntity getRolByRolId() {
        return rolByRolId;
    }

    public void setRolByRolId(RolEntity rolByRolId) {
        this.rolByRolId = rolByRolId;
    }
}
