package com.example.application.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 Exemplo de uso da função para implementação no front:

 EmailSender emailSender = new EmailSender("quizstorytelling@gmail.com", "zdalevhynyidjhid");
 try {
 emailSender.sendEmail("brunorichartzf@gmail.com", "Hello", "This is a test email.");
 } catch (MessagingException e) {
 e.printStackTrace();
 }

Ao criar uma instancia do EmailSender o email e a senha são sempre os mesmos
 .sendEmail é modificável, campo 1 é o recipiente, campo 2 é o assunto, campo 3 é o corpo do email
 **/
public class EmailSender {

    private String username;
    private String password;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String recipientEmail, String subject, String messageContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = prepareMessage(session, username, recipientEmail, subject, messageContent);

        Transport.send(message);
    }

    private Message prepareMessage(Session session, String username, String recipientEmail, String subject, String messageContent) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(messageContent);
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
