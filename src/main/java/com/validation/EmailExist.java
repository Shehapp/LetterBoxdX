package com.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailExistValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExist {

    String message() default "this email is already exist";
    boolean choice() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}