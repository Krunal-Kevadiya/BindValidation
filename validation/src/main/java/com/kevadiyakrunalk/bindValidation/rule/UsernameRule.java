package com.kevadiyakrunalk.bindValidation.rule;

import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.util.EditTextHandler;

public class UsernameRule extends TypeRule {

    public UsernameRule(TextView view, String errorMessage) {
        super(view, FieldType.Username, errorMessage);
    }

    @Override
    protected boolean isValid(TextView view) {
        String username = view.getText().toString();
        return username.matches("[a-zA-Z0-9-._]+");
    }

    @Override
    protected void onValidationSucceeded(TextView view) {
        super.onValidationSucceeded(view);
        EditTextHandler.removeError(view);
    }

    @Override
    protected void onValidationFailed(TextView view) {
        super.onValidationFailed(view);
        EditTextHandler.setError(view, errorMessage);
    }
}
