package com.apolo.modulos.roles.enums;

public enum Credencial {
    ADMIN("ADMIN"),
    COORDINADOR("COORDINADOR"),
    ACUDIENTE("ACUDIENTE"),
    ADMINISTRADOR("ADMINISTRADOR");

    private final String codigo;

    private Credencial(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
