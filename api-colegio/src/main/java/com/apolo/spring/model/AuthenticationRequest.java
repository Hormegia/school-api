package com.apolo.spring.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {


    private String correo;
    private String password;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String correo, String password) {
        this.setCorreo(correo);
        this.setPassword(password);
    }
}