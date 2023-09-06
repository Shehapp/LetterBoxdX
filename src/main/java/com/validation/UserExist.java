package com.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExist{

    String message() default "this userName is already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}