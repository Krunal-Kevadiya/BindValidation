package com.kevadiyakrunalk.bindValidation.forXml.routines.checkdigit;

public final class LuhnCheckDigit extends ModulusCheckDigit {

    private static final long serialVersionUID = -2976900113942875999L;

    public static final CheckDigit LUHN_CHECK_DIGIT = new LuhnCheckDigit();

    private static final int[] POSITION_WEIGHT = new int[] {2, 1};

    public LuhnCheckDigit() {
        super(10); // CHECKSTYLE IGNORE MagicNumber
    }

    @Override
    protected int weightedValue(int charValue, int leftPos, int rightPos) {
        int weight = POSITION_WEIGHT[rightPos % 2]; // CHECKSTYLE IGNORE MagicNumber
        int weightedValue = charValue * weight;
        return weightedValue > 9 ? (weightedValue - 9) : weightedValue; // CHECKSTYLE IGNORE MagicNumber
    }
}
