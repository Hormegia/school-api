package com.apolo.modulos.usuarios.listener;

import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.event.onRegistroUsuarioEvent;
import com.apolo.modulos.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import java.util.UUID;

@Component
public class RegistrarListener implements ApplicationListener<onRegistroUsuarioEvent> {


    private final JavaMailSender mailSender;

    private final IUsuarioService iUsuarioService;

    @Autowired
    public RegistrarListener(JavaMailSender mailSender, IUsuarioService iUsuarioService) {
        this.mailSender = mailSender;
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

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);



        Usuario usuario = event.getUsuario();
        String token = UUID.randomUUID().toString();
        iUsuarioService.crearTokenActivacion(usuario, token);

        String recipientAddress = usuario.getCorreo();
        String subject = "Confirmación Contraseña";
        String confirmationUrl = event.getAppUrl() + "/" + token;

        Context context = new Context();
        context.setVariable("urlActivar", confirmationUrl);
        String html = templateEngine.process("activacionUsuario", context);



        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(recipientAddress);
        email.setSubject(subject);
        mimeMessage.setContent(html, "text/html");
        mailSender.send(mimeMessage);
    }

}
