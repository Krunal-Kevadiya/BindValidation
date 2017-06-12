package com.kevadiyakrunalk.bindValidation.forXml.routines;

import com.kevadiyakrunalk.bindValidation.forXml.routines.checkdigit.CheckDigit;
import com.kevadiyakrunalk.bindValidation.forXml.routines.checkdigit.LuhnCheckDigit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardValidator implements Serializable {

    private static final long serialVersionUID = 5955978921148959496L;

    public static final long NONE = 0;

    public static final long AMEX = 1 << 0;

    public static final long VISA = 1 << 1;

    public static final long MASTERCARD = 1 << 2;

    public static final long DISCOVER = 1 << 3; // CHECKSTYLE IGNORE MagicNumber

    public static final long DINERS = 1 << 4; // CHECKSTYLE IGNORE MagicNumber

    public static final long VPAY = 1 << 5; // CHECKSTYLE IGNORE MagicNumber

    @Deprecated
    public static final long MASTERCARD_PRE_OCT2016 = 1 << 6; // CHECKSTYLE IGNORE MagicNumber


    private final List<CodeValidator> cardTypes = new ArrayList<CodeValidator>();

    private static final CheckDigit LUHN_VALIDATOR = LuhnCheckDigit.LUHN_CHECK_DIGIT;

    public static final CodeValidator AMEX_VALIDATOR = new CodeValidator("^(3[47]\\d{13})$", LUHN_VALIDATOR);

    public static final CodeValidator DINERS_VALIDATOR = new CodeValidator("^(30[0-5]\\d{11}|3095\\d{10}|36\\d{12}|3[8-9]\\d{12})$", LUHN_VALIDATOR);

    private static final RegexValidator DISCOVER_REGEX = new RegexValidator(new String[] {"^(6011\\d{12})$", "^(64[4-9]\\d{13})$", "^(65\\d{14})$"});

    public static final CodeValidator DISCOVER_VALIDATOR = new CodeValidator(DISCOVER_REGEX, LUHN_VALIDATOR);

    private static final RegexValidator MASTERCARD_REGEX = new RegexValidator(
        new String[] {
            "^(5[1-5]\\d{14})$",  // 51 - 55 (pre Oct 2016)
            // valid from October 2016
            "^(2221\\d{12})$",    // 222100 - 222199
            "^(222[2-9]\\d{12})$",// 222200 - 222999
            "^(22[3-9]\\d{13})$", // 223000 - 229999
            "^(2[3-6]\\d{14})$",  // 230000 - 269999
            "^(27[01]\\d{13})$",  // 270000 - 271999
            "^(2720\\d{12})$",    // 272000 - 272099
        });

    public static final CodeValidator MASTERCARD_VALIDATOR = new CodeValidator(MASTERCARD_REGEX, LUHN_VALIDATOR);

    @Deprecated
    public static final CodeValidator MASTERCARD_VALIDATOR_PRE_OCT2016 = new CodeValidator("^(5[1-5]\\d{14})$", LUHN_VALIDATOR);

    public static final CodeValidator VISA_VALIDATOR = new CodeValidator("^(4)(\\d{12}|\\d{15})$", LUHN_VALIDATOR);

    public static final CodeValidator VPAY_VALIDATOR = new CodeValidator("^(4)(\\d{12,18})$", LUHN_VALIDATOR);

    public CreditCardValidator() {
        this(AMEX + VISA + MASTERCARD + DISCOVER);
    }

    public CreditCardValidator(long options) {
        super();

        if (isOn(options, VISA)) {
            this.cardTypes.add(VISA_VALIDATOR);
        }

        if (isOn(options, VPAY)) {
            this.cardTypes.add(VPAY_VALIDATOR);
        }

        if (isOn(options, AMEX)) {
            this.cardTypes.add(AMEX_VALIDATOR);
        }

        if (isOn(options, MASTERCARD)) {
            this.cardTypes.add(MASTERCARD_VALIDATOR);
        }

        if (isOn(options, MASTERCARD_PRE_OCT2016)) {
            this.cardTypes.add(MASTERCARD_VALIDATOR_PRE_OCT2016);
        }

        if (isOn(options, DISCOVER)) {
            this.cardTypes.add(DISCOVER_VALIDATOR);
        }

        if (isOn(options, DINERS)) {
            this.cardTypes.add(DINERS_VALIDATOR);
        }
    }

    public CreditCardValidator(CodeValidator[] creditCardValidators) {
        if (creditCardValidators == null) {
            throw new IllegalArgumentException("Card validators are missing");
        }
        Collections.addAll(cardTypes, creditCardValidators);
    }

    public boolean isValid(String card) {
        if (card == null || card.length() == 0) {
            return false;
        }
        for (CodeValidator cardType : cardTypes) {
            if (cardType.isValid(card)) {
                return true;
            }
        }
        return false;
    }

    public Object validate(String card) {
        if (card == null || card.length() == 0) {
            return null;
        }
        Object result = null;
        for (CodeValidator cardType : cardTypes) {
            result = cardType.validate(card);
            if (result != null) {
                return result;
            }
        }
        return null;

    }

    private boolean isOn(long options, long flag) {
        return (options & flag) > 0;
    }
}
