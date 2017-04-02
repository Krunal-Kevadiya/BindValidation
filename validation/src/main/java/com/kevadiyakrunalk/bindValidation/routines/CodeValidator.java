package com.kevadiyakrunalk.bindValidation.routines;

import com.kevadiyakrunalk.bindValidation.routines.checkdigit.CheckDigit;

import java.io.Serializable;

public final class CodeValidator implements Serializable {

    private static final long serialVersionUID = 446960910870938233L;

    private final RegexValidator regexValidator;
    private final int minLength;
    private final int maxLength;
    private final CheckDigit checkdigit;

    public CodeValidator(String regex, CheckDigit checkdigit) {
        this(regex, -1, -1, checkdigit);
    }

    public CodeValidator(String regex, int length, CheckDigit checkdigit) {
        this(regex, length, length, checkdigit);
    }

    public CodeValidator(String regex, int minLength, int maxLength,
            CheckDigit checkdigit) {
        if (regex != null && regex.length() > 0) {
            this.regexValidator = new RegexValidator(regex);
        } else {
            this.regexValidator = null;
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.checkdigit = checkdigit;
    }

    public CodeValidator(RegexValidator regexValidator, CheckDigit checkdigit) {
        this(regexValidator, -1, -1, checkdigit);
    }

    public CodeValidator(RegexValidator regexValidator, int length, CheckDigit checkdigit) {
        this(regexValidator, length, length, checkdigit);
    }

    public CodeValidator(RegexValidator regexValidator, int minLength, int maxLength,
            CheckDigit checkdigit) {
        this.regexValidator = regexValidator;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.checkdigit = checkdigit;
    }

    public CheckDigit getCheckDigit() {
        return checkdigit;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public RegexValidator getRegexValidator() {
        return regexValidator;
    }

    public boolean isValid(String input) {
        return (validate(input) != null);
    }

    public Object validate(String input) {

        if (input == null) {
            return null;
        }

        String code = input.trim();
        if (code.length() == 0) {
            return null;
        }

        // validate/reformat using regular expression
        if (regexValidator != null) {
            code = regexValidator.validate(code);
            if (code == null) {
                return null;
            }
        }

        // check the length (must be done after validate as that can change the code)
        if ((minLength >= 0 && code.length() < minLength) ||
            (maxLength >= 0 && code.length() > maxLength)) {
            return null;
        }

        // validate the check digit
        if (checkdigit != null && !checkdigit.isValid(code)) {
            return null;
        }

        return code;

    }
}
