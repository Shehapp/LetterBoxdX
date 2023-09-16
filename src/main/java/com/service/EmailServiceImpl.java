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
    public String generateCode() {
        StringBuilder code= new StringBuilder();
        Random random=new Random();
        for (int i=0;i<8;i++){
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    @Override
    public String sendSMS(String email,String name) {
        String code=this.generateCode();
        SimpleMailMessage emailMessage=new SimpleMailMessage();

        emailMessage.setTo(email);
        emailMessage.setSubject("Confirm Email");
        emailMessage.setText("Hi, "+name+" \n this is the Code : "+code+" /n bye");
        javaMailSender.send(emailMessage);
        return code;

    }
}
