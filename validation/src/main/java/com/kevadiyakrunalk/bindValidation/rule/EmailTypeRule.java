package com.kevadiyakrunalk.bindValidation.rule;

import android.util.Patterns;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.util.EditTextHandler;

import java.util.regex.Pattern;

public class EmailTypeRule extends TypeRule {

    public EmailTypeRule(TextView view, String errorMessage) {
        super(view, FieldType.Email, errorMessage);
    }

    @Override
    protected boolean isValid(TextView view) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        return emailPattern.matcher(view.getText()).matches();
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
