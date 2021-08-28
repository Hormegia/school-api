package com.apolo;

public class Matricula {

    String estudiante;
    int codigo;

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
        return  String.format("aaaaaaaaasadasd %s   %d", estudiante, codigo);
    }
}
