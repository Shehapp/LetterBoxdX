package com.service;

public interface EmailService {
    String generateCode();
    String sendSMS(String email,String name);

}
