package com.apolo.modulos.usuarios.model;

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

    private Date fechaExpiracion;

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

        this.fechaExpiracion = calcularFechaExpiracion(EXPIRATION);
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
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }


    public void actualizarTOken(final String token) {
        this.token = token;
        this.fechaExpiracion = calcularFechaExpiracion(EXPIRATION);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(this.token).append("]").append("[Expires").append(this.fechaExpiracion).append("]");
        return builder.toString();
    }
}
