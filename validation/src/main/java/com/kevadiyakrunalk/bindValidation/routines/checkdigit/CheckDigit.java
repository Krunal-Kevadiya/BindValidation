package com.kevadiyakrunalk.bindValidation.routines.checkdigit;

public interface CheckDigit {
    String calculate(String code) throws CheckDigitException;

    boolean isValid(String code);
}
