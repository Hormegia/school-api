package com.apolo.model;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.Locale;

public class eventComplete extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private Usuario usuario;


    public eventComplete(Usuario usuario, String appUrl, Locale locale) {
        super(usuario);
        this.appUrl = appUrl;
        this.locale = locale;
        this.usuario = usuario;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
