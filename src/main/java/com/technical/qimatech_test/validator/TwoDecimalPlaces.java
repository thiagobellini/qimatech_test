package com.technical.qimatech_test.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TwoDecimalPlacesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TwoDecimalPlaces {

    String message() default "The price must have a maximum of two decimal places";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
