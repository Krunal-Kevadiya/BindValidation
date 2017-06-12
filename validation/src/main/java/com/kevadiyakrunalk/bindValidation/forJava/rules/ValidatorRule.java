package com.kevadiyakrunalk.bindValidation.forJava.rules;

import com.kevadiyakrunalk.bindValidation.forJava.Valid;

public class ValidatorRule<T> extends AbstractRule<T> {

    private final Valid<T> validator;

    public ValidatorRule(Valid<T> validator, String error) {
        super(error);
        this.validator = validator;
    }

    @Override
    public boolean isValid(T t) {
        return validator.isValid(t);
    }
}
