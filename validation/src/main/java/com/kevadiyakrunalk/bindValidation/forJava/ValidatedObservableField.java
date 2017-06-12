package com.kevadiyakrunalk.bindValidation.forJava;

import android.databinding.BaseObservable;

public class ValidatedObservableField<T> extends BaseObservable {
    private Rule<T> rule;
    private T value;
    private boolean isValid;
    private String errorMessage;

    public ValidatedObservableField(T value, Rule<T> rule, boolean validate) {
        this.value = value;
        this.rule = rule;
        if (validate) {
            validate();
        }
    }

    public ValidatedObservableField(T value, Rule<T> rule) {
        this(value, rule, false);
    }

    public ValidatedObservableField(T value) {
        this(value, null, false);
    }

    public ValidatedObservableField() {
    }

    public void setValue(T value) {
        if (value != null && !value.equals(this.value)) {
            this.value = value;
            if (!validate()) {
                notifyChange();
            }
        }
    }

    public void setRule(Rule<T> rule) {
        this.rule = rule;
    }

    public T getValue() {
        return value;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        if (errorMessage == null) {
            hideErrorMessage();
        } else if (!errorMessage.equals(this.errorMessage)) {
            this.errorMessage = errorMessage;
            notifyChange();
        }
    }

    public boolean validate() {
        if (rule != null) {
            isValid = rule.isValid(getValue());
            errorMessage = isValid ? null : rule.getErrorMessage();
            notifyChange();
            return true;
        }
        return false;
    }

    public void hideErrorMessage() {
        if (errorMessage != null) {
            errorMessage = null;
            notifyChange();
        }
    }
}
