package com.kevadiyakrunalk.bindValidation.forXml.routines;

import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateValidator extends AbstractCalendarValidator {

    private static final long serialVersionUID = -3966328400469953190L;
    private static final DateValidator VALIDATOR = new DateValidator();

    public static DateValidator getInstance() {
        return VALIDATOR;
    }

    public DateValidator() {
        this(true, DateFormat.SHORT);
    }

    public DateValidator(boolean strict, int dateStyle) {
        super(strict, dateStyle, -1);
    }

    public Date validate(String value) {
        return (Date)parse(value, (String)null, (Locale)null, (TimeZone)null);
    }

    public Date validate(String value, TimeZone timeZone) {
        return (Date)parse(value, (String)null, (Locale)null, timeZone);
    }

    public Date validate(String value, String pattern) {
        return (Date)parse(value, pattern, (Locale)null, (TimeZone)null);
    }

    public Date validate(String value, String pattern, TimeZone timeZone) {
        return (Date)parse(value, pattern, (Locale)null, timeZone);
    }

    public Date validate(String value, Locale locale) {
        return (Date)parse(value, (String)null, locale, (TimeZone)null);
    }

    public Date validate(String value, Locale locale, TimeZone timeZone) {
        return (Date)parse(value, (String)null, locale, timeZone);
    }

    public Date validate(String value, String pattern, Locale locale) {
        return (Date)parse(value, pattern, locale, (TimeZone)null);
    }

    public Date validate(String value, String pattern, Locale locale, TimeZone timeZone) {
        return (Date)parse(value, pattern, locale, timeZone);
    }

    public int compareDates(Date value, Date compare, TimeZone timeZone) {
        Calendar calendarValue   = getCalendar(value, timeZone);
        Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.DATE);
    }

    public int compareWeeks(Date value, Date compare, TimeZone timeZone) {
        Calendar calendarValue   = getCalendar(value, timeZone);
        Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.WEEK_OF_YEAR);
    }

    public int compareMonths(Date value, Date compare, TimeZone timeZone) {
        Calendar calendarValue   = getCalendar(value, timeZone);
        Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.MONTH);
    }

    public int compareQuarters(Date value, Date compare, TimeZone timeZone) {
        return compareQuarters(value, compare, timeZone, 1);
    }

    public int compareQuarters(Date value, Date compare, TimeZone timeZone, int monthOfFirstQuarter) {
        Calendar calendarValue   = getCalendar(value, timeZone);
        Calendar calendarCompare = getCalendar(compare, timeZone);
        return super.compareQuarters(calendarValue, calendarCompare, monthOfFirstQuarter);
    }

    public int compareYears(Date value, Date compare, TimeZone timeZone) {
        Calendar calendarValue   = getCalendar(value, timeZone);
        Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.YEAR);
    }

    @Override
    protected Object processParsedValue(Object value, Format formatter) {
        return value;
    }

    private Calendar getCalendar(Date value, TimeZone timeZone) {

        Calendar calendar = null;
        if (timeZone != null) {
            calendar = Calendar.getInstance(timeZone);
        } else {
            calendar = Calendar.getInstance();
        }
        calendar.setTime(value);
        return calendar;

    }
}
