package com.kevadiyakrunalk.bindValidation.forXml.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.forXml.rule.EmptyRule;
import com.kevadiyakrunalk.bindValidation.forXml.rule.MaxLengthRule;
import com.kevadiyakrunalk.bindValidation.forXml.rule.MinLengthRule;
import com.kevadiyakrunalk.bindValidation.forXml.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.forXml.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.forXml.util.ViewTagHelper;

public class LengthBindings {

    @BindingAdapter(value = {"validateMinLength", "validateMinLengthMessage", "validateDateMinListener"}, requireAll = false)
    public static void bindingMinLength(TextView view, int minLength, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_min_length, minLength);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new MinLengthRule(view, minLength, handledErrorMessage));
    }

    @BindingAdapter(value = {"validateMaxLength", "validateMaxLengthMessage", "validateDateMaxListener"}, requireAll = false)
    public static void bindingMaxLength(TextView view, int maxLength, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_max_length, maxLength);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new MaxLengthRule(view, maxLength, handledErrorMessage));
    }

    @BindingAdapter(value = {"validateEmpty", "validateEmptyMessage", "validateDateListener"}, requireAll = false)
    public static void bindingEmpty(TextView view, boolean empty, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_empty_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new EmptyRule(view, empty, handledErrorMessage));
    }
}
