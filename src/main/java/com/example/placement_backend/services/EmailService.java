package com.example.placement_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(String to, String subject, String body) {
        MimeMessage message=emailSender.createMimeMessage();
        try {
            message.setSubject(subject);
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("noreply@foodwastage.com");
            helper.setTo(to);
            helper.setText(body,true);
            emailSender.send(message);
        }catch(Exception ex) {
            System.out.println("Error "+ex.getMessage());
        }
    }
}
