package com.validation;


import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistValidator implements ConstraintValidator<EmailExist, String> {

    @Autowired
    UserService userService;

    boolean choice;
    @Override
    public void initialize(EmailExist constraintAnnotation) {
        choice = constraintAnnotation.choice();
    }


    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return true;
        }
        if(choice){
            return userService.isEmailExist(s);
        }else
            return !userService.isEmailExist(s);
    }
}