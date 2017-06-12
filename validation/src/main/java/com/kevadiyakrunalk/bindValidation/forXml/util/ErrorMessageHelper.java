package com.kevadiyakrunalk.bindValidation.forXml.util;

import android.support.annotation.StringRes;
import android.view.View;

public class ErrorMessageHelper {

    public static String getStringOrDefault(View view, String errorMessage,
                                            @StringRes int defaultMessage) {
        return errorMessage != null ? errorMessage : view.getContext().getString(defaultMessage);
    }

    public static String getStringOrDefault(View view, String errorMessage,
                                            @StringRes int defaultMessage, int value) {
        return errorMessage != null ? errorMessage : view.getContext().getString(defaultMessage, value);
    }

    public static String getStringOrDefault(View view, CharSequence errorMessage,
                                            @StringRes int defaultMessage) {
        return errorMessage != null ? errorMessage.toString() : view.getContext().getString(defaultMessage);
    }

    public static String getStringOrDefault(View view, CharSequence errorMessage,
                                            @StringRes int defaultMessage, int value) {
        return errorMessage != null ? errorMessage.toString() : view.getContext().getString(defaultMessage, value);
    }
}
