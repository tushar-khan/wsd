package com.solvians.showcase;

import com.solvians.showcase.enums.LetterValueEnum;

import java.util.concurrent.ThreadLocalRandom;

public class IsinGenerator {
    public String generateIsin(String isinWithoutCheckDigit) {
        return isinWithoutCheckDigit + getCheckDigit(isinWithoutCheckDigit);
    }

    public String get2randomLetters() {
        return ThreadLocalRandom.current()
                .ints('A', 'Z' + 1)
                .limit(2)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String get9randomAlphaNumerics() {
        return ThreadLocalRandom.current()
                .ints(0, 36)
                .limit(9)
                .mapToObj(i -> i < 10 ? String.valueOf(i) : String.valueOf((char) ('A' + i - 10)))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private int getCheckDigit(String input) {
        String onlyDigitsInput = convertLettersToDigits(input);
        String multiplyDigitsAltering = multiplyDigitsBy2AlternatingFromRight(onlyDigitsInput);
        int sumOfDigits = sumDigits(multiplyDigitsAltering);
        return (10 - sumOfDigits % 10) % 10;
    }

    private String convertLettersToDigits(String input) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                Integer digit = LetterValueEnum.getValueByLetter(Character.toUpperCase(ch));
                result.append(digit);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    private String multiplyDigitsBy2AlternatingFromRight(String input) {
        StringBuilder result = new StringBuilder();
        boolean isEven = input.length() % 2 == 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                int digit = Character.getNumericValue(ch);
                if (isEven) {
                    if (i % 2 == 1) {
                        digit *= 2;
                    }
                    result.append(digit);
                } else {
                    if (i % 2 == 0) {
                        digit *= 2;
                    }
                    result.append(digit);
                }
            }
        }
        return result.toString();
    }

    private int sumDigits(String input) {
        int sum = 0;
        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                sum += Character.getNumericValue(ch);
            }
        }
        return sum;
    }
}
