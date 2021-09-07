package com.apolo.model;

import com.apolo.model.service.IUsuarioService;
import com.apolo.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

@Component
public class LIstener implements ApplicationListener<eventComplete> {


    private MessageSource messages;


    private JavaMailSender mailSender;

    private IUsuarioService iUsuarioService;

    @Autowired
    public LIstener(MessageSource messages, JavaMailSender mailSender, IUsuarioService iUsuarioService) {
        this.messages = messages;
        this.mailSender = mailSender;
        this.iUsuarioService = iUsuarioService;
    }



    @Override
    public void onApplicationEvent(eventComplete eventComplete) {
        this.confirmRegistration(eventComplete);
    }

    private void confirmRegistration(eventComplete event) {
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
