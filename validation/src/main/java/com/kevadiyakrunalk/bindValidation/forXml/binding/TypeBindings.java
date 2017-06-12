package com.kevadiyakrunalk.bindValidation.forXml.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.forXml.rule.TypeRule;
import com.kevadiyakrunalk.bindValidation.forXml.util.EditTextHandler;
import com.kevadiyakrunalk.bindValidation.forXml.util.ErrorMessageHelper;
import com.kevadiyakrunalk.bindValidation.forXml.util.ViewTagHelper;

public class TypeBindings {

    @BindingAdapter(value = {"validateType", "validateTypeMessage", "validateDateListener"}, requireAll = false)
    public static void bindingTypeValidation(TextView view, String fieldTypeText, String errorMessage, String listener) {
        EditTextHandler.setListener(view, listener);

        TypeRule.FieldType fieldType = getFieldTypeByText(fieldTypeText);
        try {
            String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                    errorMessage, fieldType.errorMessageId);
            ViewTagHelper.appendValue(R.id.validator_rule, view, fieldType.instantiate(view, handledErrorMessage));
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    @NonNull
    private static TypeRule.FieldType getFieldTypeByText(String fieldTypeText) {
        TypeRule.FieldType fieldType = TypeRule.FieldType.None;
        for (TypeRule.FieldType type : TypeRule.FieldType.values()) {
            if (type.toString().equalsIgnoreCase(fieldTypeText)) {
                fieldType = type;
                break;
            }
        }
        return fieldType;
    }
}
