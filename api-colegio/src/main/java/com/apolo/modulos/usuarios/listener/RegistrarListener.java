package com.apolo.modulos.usuarios.listener;

import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.event.onRegistroUsuarioEvent;
import com.apolo.modulos.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrarListener implements ApplicationListener<onRegistroUsuarioEvent> {



    private JavaMailSender mailSender;

    private IUsuarioService iUsuarioService;

    @Autowired
    public RegistrarListener(JavaMailSender mailSender, IUsuarioService iUsuarioService) {
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
        email.setText(  message + "\r\n"  + confirmationUrl);
        mailSender.send(email);
    }

}
