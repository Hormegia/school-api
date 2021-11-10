package com.apolo.modulos.usuarios.listener;

import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.event.onRegistroUsuarioEvent;
import com.apolo.modulos.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import java.util.UUID;

@Component
public class RegistrarListener implements ApplicationListener<onRegistroUsuarioEvent> {


    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    private final UsuarioService iUsuarioService;

    @Autowired
    public RegistrarListener(JavaMailSender mailSender, TemplateEngine templateEngine, UsuarioService iUsuarioService) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.iUsuarioService = iUsuarioService;
    }


    @Override
    public void onApplicationEvent(onRegistroUsuarioEvent onRegistroUsuarioEvent) {
        try {
            this.confirmRegistration(onRegistroUsuarioEvent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(onRegistroUsuarioEvent event) throws MessagingException {

        Usuario usuario = event.getUsuario();
        String token = UUID.randomUUID().toString();
        iUsuarioService.crearTokenActivacion(usuario, token);

        iUsuarioService.enviarCorreoConfirmacion(usuario, token, event.getAppUrl());

    }

}
