package com.kevadiyakrunalk.bindValidation.forXml.util;

import android.widget.TextView;

public interface ValidatorListener {
    void onValidatorError(TextView textView, String errorMessage);
}