package com.apolo.listener;

import com.apolo.model.Usuario;
import com.apolo.event.onRegistroUsuarioEvent;
import com.apolo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrarListener implements ApplicationListener<onRegistroUsuarioEvent> {


    private MessageSource messages;


    private JavaMailSender mailSender;

    private IUsuarioService iUsuarioService;

    @Autowired
    public RegistrarListener(MessageSource messages, JavaMailSender mailSender, IUsuarioService iUsuarioService) {
        this.messages = messages;
        this.mailSender = mailSender;
        this.iUsuarioService = iUsuarioService;
    }



    @Override
    public void onApplicationEvent(onRegistroUsuarioEvent onRegistroUsuarioEvent) {
        this.confirmRegistration(onRegistroUsuarioEvent);
    }

    private void confirmRegistration(onRegistroUsuarioEvent event) {
        Usuario usuario = event.getUsuario();
        String token = UUID.randomUUID().toString();
        iUsuarioService.crearTokenActivacion(usuario, token);

        String recipientAddress = usuario.getCorreo();
        String subject = "Confirmación Contraseña";
        String confirmationUrl = event.getAppUrl() + "/" +token;

        String message = "EXSITOSOOOO";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(  message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }

}
