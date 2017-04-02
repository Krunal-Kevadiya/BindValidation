package com.kevadiyakrunalk.bindValidation.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.rule.RegexRule;
import com.kevadiyakrunalk.bindValidation.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.util.ViewTagHelper;

public class RegexBindings {

    @BindingAdapter(value = {"validateRegex", "validateRegexMessage", "validateDateListener"}, requireAll = false)
    public static void bindingRegex(TextView view, String pattern, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_regex_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new RegexRule(view, pattern, handledErrorMessage));
    }
}
