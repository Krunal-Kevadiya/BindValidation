package com.kevadiyakrunalk.bindValidation.forXml.util;

import android.databinding.adapters.ListenerUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import com.kevadiyakrunalk.bindValidation.R;
import com.kevadiyakrunalk.bindValidation.forXml.Validator;

public class EditTextHandler {

    public static void removeError(TextView textView) {
        if (Validator.listener == null)
            EditTextHandler.setError(textView, null);
    }

    public static void setError(TextView textView, String errorMessage) {
        if (Validator.listener == null) {
            TextInputLayout textInputLayout = getTextInputLayout(textView);
            if (textInputLayout != null) {
                textInputLayout.setErrorEnabled(!TextUtils.isEmpty(errorMessage));
                textInputLayout.setError(errorMessage);
            } else
                textView.setError(errorMessage);
        } else
            Validator.listener.onValidatorError(textView, errorMessage);
    }

    @Nullable
    private static TextInputLayout getTextInputLayout(TextView textView) {
        TextInputLayout textInputLayout = null;

        ViewParent parent = textView.getParent();
        while (parent instanceof View) {
            if (parent instanceof TextInputLayout) {
                textInputLayout = (TextInputLayout) parent;
                break;
            }
            parent = parent.getParent();
        }
        return textInputLayout;
    }

    public static void setListener(TextView view, String listener) {
        listener = listener == null ? "" : listener;
        if (listener.equalsIgnoreCase("onTextChange"))
            EditTextHandler.disableErrorOnTextChanged(view);
        else if (listener.equalsIgnoreCase("onFocusChange"))
            EditTextHandler.disableErrorOnFocusChange(view);
        else if (listener.equalsIgnoreCase("onKeyPress"))
            EditTextHandler.disableErrorOnKeyPress(view);
        else
            EditTextHandler.disableErrorOnTextChanged(view);
    }

    private static void disableErrorOnTextChanged(final TextView textView) {
        if (ListenerUtil.<TextWatcher>getListener(textView, R.id.text_watcher_clear_error) != null) {
            return;
        }

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //EditTextHandler.setError(textView, null);
                  Validator.sSingleton.isViewValid(textView);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        ListenerUtil.trackListener(textView, textView, R.id.text_watcher_clear_error);
    }

    private static void disableErrorOnFocusChange(final TextView textView) {
        if (ListenerUtil.<View.OnFocusChangeListener>getListener(textView, R.id.text_focus_clear_error) != null) {
            return;
        }

        final View.OnFocusChangeListener textWatcher = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    Validator.sSingleton.isViewValid(v);
            }
        };
        textView.setOnFocusChangeListener(textWatcher);
        ListenerUtil.trackListener(textView, textView, R.id.text_focus_clear_error);
    }

    private static void disableErrorOnKeyPress(final TextView textView) {
        if (ListenerUtil.<EditText.OnEditorActionListener>getListener(textView, R.id.text_key_press_clear_error) != null) {
            return;
        }

        final EditText.OnEditorActionListener textWatcher = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (Validator.sSingleton.getKeyPress().length > 0) {
                    for (int keys : Validator.sSingleton.getKeyPress()) {
                        if (keys == actionId) {
                            Validator.sSingleton.isViewValid(v);
                            return false;
                        }
                    }
                    return true;
                } else {
                    Validator.sSingleton.isViewValid(v);
                    return false;
                }
            }
        };
        textView.setOnEditorActionListener(textWatcher);
        ListenerUtil.trackListener(textView, textView, R.id.text_key_press_clear_error);
    }
}
