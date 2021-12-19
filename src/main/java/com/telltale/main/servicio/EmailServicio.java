package com.telltale.main.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromMail;

    private static final String SUBJECT = "¡Bienvenido a la comunidad de TellTale: Historia x Historia!";

    @Async
    public void sendMail(String username, String mailTo) {
        SimpleMailMessage message = new SimpleMailMessage(); //si vamos a enviar un HTML o imágenes hay que usar "MimeMessage message = new MimeMessage();"
        message.setTo(mailTo);
        message.setFrom(fromMail);
        message.setSubject(SUBJECT);
        message.setText("¡Hola "+ username + "!\n¡Bienvenidx a nuestra comunidad!\n¡Gracias por registrarte!\n¿Ya estás listx para escribir tu primer historia?");
        sender.send(message);
    }

/*
    //este método produce otro hilo para enviar el mail. Funciona de manera asíncrona pero sin utilizar notaciones Spring
    public void sendMailByThread(String username, String mailTo) {
        new Thread(() -> {
            SimpleMailMessage message = new SimpleMailMessage(); //si vamos a enviar un HTML o imágenes hay que usar "MimeMessage message = new MimeMessage();"
            message.setTo(mailTo);
            message.setFrom(fromMail);
            message.setSubject(SUBJECT);
            message.setText("¡Hola "+ (username.toUpperCase()) + "!\n¡Bienvenido a nuestra página!\n¡Gracias por registrarte!");
            sender.send(message);
        }).start();
    }
*/

}
