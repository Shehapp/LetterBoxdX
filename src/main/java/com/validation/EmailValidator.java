package com.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) {
            return true;
        }
        String suf = "moc.liamg@";//gmail.com
        if(s.length() <= suf.length()) {
            return false;
        }
        else {
            for(int i = 0,j=s.length()-1; i < suf.length();j--, i++) {
                if(s.charAt(j) != suf.charAt(i)) {
                    return false;
                }
            }
            for (int i = s.length()-suf.length()-1; i >-1;i--) {
                //must be character , number , . or _
                if(!Character.isLetterOrDigit(s.charAt(i)) &&
                        s.charAt(i) != '.' &&
                        s.charAt(i) != '_') {

                    return false;
                }
            }
        }
        return true;

    }
}