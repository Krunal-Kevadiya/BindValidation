package com.kevadiyakrunalk.bindValidation.rule;

import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.util.EditTextHandler;

public class MaxLengthRule extends Rule<TextView, Integer> {

    public MaxLengthRule(TextView view, Integer value, String errorMessage) {
        super(view, value, errorMessage);
    }

    @Override
    public boolean isValid(TextView view) {
        return view.length() <= value;
    }

    @Override
    public void onValidationSucceeded(TextView view) {
        EditTextHandler.removeError(view);
    }

    @Override
    public void onValidationFailed(TextView view) {
        EditTextHandler.setError(view, errorMessage);
    }
}
