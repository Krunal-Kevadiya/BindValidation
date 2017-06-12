package com.kevadiyakrunalk.bindValidation.forXml.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.forXml.rule.ConfirmPasswordRule;
import com.kevadiyakrunalk.bindValidation.forXml.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.forXml.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.forXml.util.ViewTagHelper;

public class PasswordBindings {

    @BindingAdapter(value = {"validatePassword", "validatePasswordMessage", "validateDateListener"}, requireAll = false)
    public static void bindingPassword(TextView view, TextView comparableView, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_not_equal_password);
        ViewTagHelper.appendValue(R.id.validator_rule, view,
                new ConfirmPasswordRule(view, comparableView, handledErrorMessage));
    }
}
