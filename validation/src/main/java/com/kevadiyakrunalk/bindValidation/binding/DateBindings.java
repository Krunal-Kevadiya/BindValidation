package com.kevadiyakrunalk.bindValidation.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.rule.DateRule;
import com.kevadiyakrunalk.bindValidation.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.util.ViewTagHelper;

public class DateBindings {

    @BindingAdapter(value = {"validateDate", "validateDateMessage", "validateDateListener"}, requireAll = false)
    public static void bindingDate(TextView view, String pattern, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_date_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new DateRule(view, pattern, handledErrorMessage));
    }
}
