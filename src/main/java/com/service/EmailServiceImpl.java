package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;



    @Override
    public void sendSMS(String email,String name,String token) {
        SimpleMailMessage emailMessage=new SimpleMailMessage();

        emailMessage.setTo(email);
        emailMessage.setSubject("Confirm Email");
        String confirmationLink = String.format("http://localhost:8080/confirm/%s", token);
        String emailText = String.format(
                "Hello %s,\n\n"
                        + "Please click on the link below to confirm your email:\n"
                        + "%s\n\n"
                        + "Thank you,\n"
                        + "/LetterBoxdX", name, confirmationLink);
        emailMessage.setText(emailText);
        javaMailSender.send(emailMessage);

    }

}
