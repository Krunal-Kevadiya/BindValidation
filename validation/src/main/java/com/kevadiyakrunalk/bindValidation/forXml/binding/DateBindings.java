package com.kevadiyakrunalk.bindValidation.forXml.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.forXml.rule.DateRule;
import com.kevadiyakrunalk.bindValidation.forXml.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.forXml.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.forXml.util.ViewTagHelper;

public class DateBindings {

    @BindingAdapter(value = {"validateDate", "validateDateMessage", "validateDateListener"}, requireAll = false)
    public static void bindingDate(TextView view, String pattern, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_date_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new DateRule(view, pattern, handledErrorMessage));
    }
}
