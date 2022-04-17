package com.abiha.springboot.bootcampproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


     @Autowired
      public EmailService(JavaMailSender javaMailSender) {this.mailSender = javaMailSender;
    }
    
    @Async
    public void sendSimpleMailMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //sender email
        mailMessage.setFrom("ummay.abiha@tothenew.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);

        System.out.println("Mail sent successfully!!");
    }
}