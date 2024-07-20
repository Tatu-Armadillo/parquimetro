package br.com.fiap.parquimetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final JavaMailSender emailSender;

    @Autowired
    public MessageService(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleEmailMessage(final String to, final String subject, final String text) {
        final var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
