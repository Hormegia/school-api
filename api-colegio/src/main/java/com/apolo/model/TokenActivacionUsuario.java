package com.apolo.model;

import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class TokenActivacionUsuario {

    //Todo pasar a yml
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @ManyToOne (targetEntity = Usuario.class, fetch = FetchType.EAGER)
    private Usuario usuario;

    private Date FechaExpiracion;

    private Date calcularFechaExpiracion (int tiempoExpiracionEnMinutos){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, tiempoExpiracionEnMinutos);
        return new Date(cal.getTime().getTime());
    }

    public TokenActivacionUsuario() {

    }

    public TokenActivacionUsuario(Usuario usuario, String token) {
        this.token = token;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaExpiracion() {
        return FechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        FechaExpiracion = fechaExpiracion;
    }
}
