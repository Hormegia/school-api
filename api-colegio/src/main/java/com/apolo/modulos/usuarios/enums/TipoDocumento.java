package com.apolo.modulos.usuarios.enums;

public enum TipoDocumento {
    CC("CC");

    private final String codigo;

    private TipoDocumento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
