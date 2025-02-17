package com.technical.qimatech_test.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class TwoDecimalPlacesValidator implements ConstraintValidator<TwoDecimalPlaces, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.scale() <= 2;
    }
}
