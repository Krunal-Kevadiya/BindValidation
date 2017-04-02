package com.kevadiyakrunalk.bindValidation.routines;

import java.io.Serializable;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;

public abstract class AbstractFormatValidator implements Serializable {

    private static final long serialVersionUID = -4690687565200568258L;
    private final boolean strict;

    public AbstractFormatValidator(boolean strict) {
        this.strict = strict;
    }

    public boolean isStrict() {
        return strict;
    }

    public boolean isValid(String value) {
        return isValid(value, (String)null, (Locale)null);
    }

    public boolean isValid(String value, String pattern) {
        return isValid(value, pattern, (Locale)null);
    }

    public boolean isValid(String value, Locale locale) {
        return isValid(value, (String)null, locale);
    }

    public abstract boolean isValid(String value, String pattern, Locale locale);

    public String format(Object value) {
        return format(value, (String)null, (Locale)null);
    }

    public String format(Object value, String pattern) {
        return format(value, pattern, (Locale)null);
    }

    public String format(Object value, Locale locale) {
        return format(value, (String)null, locale);
    }

    public String format(Object value, String pattern, Locale locale) {
        Format formatter = getFormat(pattern, locale);
        return format(value, formatter);
    }

    protected String format(Object value, Format formatter) {
        return formatter.format(value);
    }

    protected Object parse(String value, Format formatter) {

        ParsePosition pos = new ParsePosition(0);
        Object parsedValue = formatter.parseObject(value, pos);
        if (pos.getErrorIndex() > -1) {
            return null;
        }

        if (isStrict() && pos.getIndex() < value.length()) {
            return null;
        }

        if (parsedValue != null) {
            parsedValue = processParsedValue(parsedValue, formatter);
        }

        return parsedValue;

    }

    protected abstract Object processParsedValue(Object value, Format formatter);

    protected abstract Format getFormat(String pattern, Locale locale);
}
