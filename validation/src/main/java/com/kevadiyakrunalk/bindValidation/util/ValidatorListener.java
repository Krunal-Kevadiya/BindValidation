package com.kevadiyakrunalk.bindValidation.util;

import android.widget.TextView;

public interface ValidatorListener {
    void onValidatorError(TextView textView, String errorMessage);
}