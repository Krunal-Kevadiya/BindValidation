package com.kevadiyakrunalk.bindValidation.forJava.rules;

import com.kevadiyakrunalk.bindValidation.forJava.Rule;

public abstract class AbstractRule<T> implements Rule<T> {

    private final String error;

    protected AbstractRule(String error) {
        this.error = error;
    }

    @Override
    public String getErrorMessage() {
        return error;
    }
}
