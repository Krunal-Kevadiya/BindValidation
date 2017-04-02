package com.kevadiyakrunalk.bindValidation.routines;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public abstract class AbstractCalendarValidator extends AbstractFormatValidator {

    private static final long serialVersionUID = -1410008585975827379L;
    private final int dateStyle;
    private final int timeStyle;

    public AbstractCalendarValidator(boolean strict, int dateStyle, int timeStyle) {
        super(strict);
        this.dateStyle = dateStyle;
        this.timeStyle = timeStyle;
    }

    @Override
    public boolean isValid(String value, String pattern, Locale locale) {
        Object parsedValue = parse(value, pattern, locale, (TimeZone)null);
        return (parsedValue == null ? false : true);
    }

    public String format(Object value, TimeZone timeZone) {
        return format(value, (String)null, (Locale)null, timeZone);
    }

    public String format(Object value, String pattern, TimeZone timeZone) {
        return format(value, pattern, (Locale)null, timeZone);
    }

    public String format(Object value, Locale locale, TimeZone timeZone) {
        return format(value, (String)null, locale, timeZone);
    }

    @Override
    public String format(Object value, String pattern, Locale locale) {
        return format(value, pattern, locale, (TimeZone)null);
    }

    public String format(Object value, String pattern, Locale locale, TimeZone timeZone) {
        DateFormat formatter = (DateFormat)getFormat(pattern, locale);
        if (timeZone != null) {
            formatter.setTimeZone(timeZone);
        } else if (value instanceof Calendar) {
            formatter.setTimeZone(((Calendar)value).getTimeZone());
        }
        return format(value, formatter);
    }

    @Override
    protected String format(Object value, Format formatter) {
        if (value == null) {
            return null;
        } else if (value instanceof Calendar) {
            value = ((Calendar)value).getTime();
        }
        return formatter.format(value);
    }

    protected Object parse(String value, String pattern, Locale locale, TimeZone timeZone) {

        value = (value == null ? null : value.trim());
        if (value == null || value.length() == 0) {
            return null;
        }
        DateFormat formatter = (DateFormat)getFormat(pattern, locale);
        if (timeZone != null) {
            formatter.setTimeZone(timeZone);
        }
        return parse(value, formatter);

    }

    @Override
    protected abstract Object processParsedValue(Object value, Format formatter);

    @Override
    protected Format getFormat(String pattern, Locale locale) {
        DateFormat formatter = null;
        boolean usePattern = (pattern != null && pattern.length() > 0);
        if (!usePattern) {
            formatter = (DateFormat)getFormat(locale);
        } else if (locale == null) {
            formatter = new SimpleDateFormat(pattern);
        } else {
            DateFormatSymbols symbols = new DateFormatSymbols(locale);
            formatter = new SimpleDateFormat(pattern, symbols);
        }
        formatter.setLenient(false);
        return formatter;
    }

    protected Format getFormat(Locale locale) {

        DateFormat formatter = null;
        if (dateStyle >= 0 && timeStyle >= 0) {
            if (locale == null) {
                formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle);
            } else {
                formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
            }
        } else if (timeStyle >= 0) {
            if (locale == null) {
                formatter = DateFormat.getTimeInstance(timeStyle);
            } else {
                formatter = DateFormat.getTimeInstance(timeStyle, locale);
            }
        } else {
            int useDateStyle = dateStyle >= 0 ? dateStyle : DateFormat.SHORT;
            if (locale == null) {
                formatter = DateFormat.getDateInstance(useDateStyle);
            } else {
                formatter = DateFormat.getDateInstance(useDateStyle, locale);
            }
        }
        formatter.setLenient(false);
        return formatter;

    }

    protected int compare(Calendar value, Calendar compare, int field) {

        int result = 0;

        // Compare Year
        result = calculateCompareResult(value, compare, Calendar.YEAR);
        if (result != 0 || field == Calendar.YEAR) {
            return result;
        }

        // Compare Week of Year
        if (field == Calendar.WEEK_OF_YEAR) {
            return calculateCompareResult(value, compare, Calendar.WEEK_OF_YEAR);
        }

        // Compare Day of the Year
        if (field == Calendar.DAY_OF_YEAR) {
            return calculateCompareResult(value, compare, Calendar.DAY_OF_YEAR);
        }

        // Compare Month
        result = calculateCompareResult(value, compare, Calendar.MONTH);
        if (result != 0 || field == Calendar.MONTH) {
            return result;
        }

        // Compare Week of Month
        if (field == Calendar.WEEK_OF_MONTH) {
            return calculateCompareResult(value, compare, Calendar.WEEK_OF_MONTH);
        }

        // Compare Date
        result = calculateCompareResult(value, compare, Calendar.DATE);
        if (result != 0 || (field == Calendar.DATE ||
                          field == Calendar.DAY_OF_WEEK ||
                          field == Calendar.DAY_OF_WEEK_IN_MONTH)) {
            return result;
        }

        // Compare Time fields
        return compareTime(value, compare, field);

    }

    protected int compareTime(Calendar value, Calendar compare, int field) {

        int result = 0;

        // Compare Hour
        result = calculateCompareResult(value, compare, Calendar.HOUR_OF_DAY);
        if (result != 0 || (field == Calendar.HOUR || field == Calendar.HOUR_OF_DAY)) {
            return result;
        }

        // Compare Minute
        result = calculateCompareResult(value, compare, Calendar.MINUTE);
        if (result != 0 || field == Calendar.MINUTE) {
            return result;
        }

        // Compare Second
        result = calculateCompareResult(value, compare, Calendar.SECOND);
        if (result != 0 || field == Calendar.SECOND) {
            return result;
        }

        // Compare Milliseconds
        if (field == Calendar.MILLISECOND) {
            return calculateCompareResult(value, compare, Calendar.MILLISECOND);
        }

        throw new IllegalArgumentException("Invalid field: " + field);

    }

    protected int compareQuarters(Calendar value, Calendar compare, int monthOfFirstQuarter) {
        int valueQuarter   = calculateQuarter(value, monthOfFirstQuarter);
        int compareQuarter = calculateQuarter(compare, monthOfFirstQuarter);
        if (valueQuarter < compareQuarter) {
            return -1;
        } else if (valueQuarter > compareQuarter) {
            return 1;
        } else {
            return 0;
        }
    }

    private int calculateQuarter(Calendar calendar, int monthOfFirstQuarter) {
        // Add Year
        int year = calendar.get(Calendar.YEAR);

        int month = (calendar.get(Calendar.MONTH) + 1);
        int relativeMonth = (month >= monthOfFirstQuarter)
                          ? (month - monthOfFirstQuarter)
                          : (month + (12 - monthOfFirstQuarter)); // CHECKSTYLE IGNORE MagicNumber
        int quarter = ((relativeMonth / 3) + 1); // CHECKSTYLE IGNORE MagicNumber
        // adjust the year if the quarter doesn't start in January
        if (month < monthOfFirstQuarter) {
            --year;
        }
        return (year * 10) + quarter; // CHECKSTYLE IGNORE MagicNumber
    }

    private int calculateCompareResult(Calendar value, Calendar compare, int field) {
        int difference = value.get(field) - compare.get(field);
        if (difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
