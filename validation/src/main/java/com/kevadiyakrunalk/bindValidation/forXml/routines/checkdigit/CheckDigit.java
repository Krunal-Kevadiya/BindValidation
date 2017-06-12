package com.kevadiyakrunalk.bindValidation.forXml.routines.checkdigit;

public interface CheckDigit {
    String calculate(String code) throws CheckDigitException;

    boolean isValid(String code);
}
