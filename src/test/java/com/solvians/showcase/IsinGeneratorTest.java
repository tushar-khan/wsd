package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsinGeneratorTest {

    @Test
    public void testGenerateIsin() {
        String sampleInput = "DE123456789";
        String sampleOutput = "DE1234567896";
        IsinGenerator isinGenerator = new IsinGenerator();
        String isin = isinGenerator.generateIsin(sampleInput);
        System.out.printf("Generated ISIN: %s", isin);
        assertEquals(isin, sampleOutput);
        assertEquals(12, isin.length(), "ISIN should be 12 characters long");
        String letters = isin.substring(0, 2);
        String alphanumerics = isin.substring(2, 11);

        assertTrue(letters.chars().allMatch(Character::isUpperCase), "First two characters should be uppercase letters");
        assertTrue(alphanumerics.chars().allMatch(c -> Character.isDigit(c) || Character.isLetter(c)), "Next 9 characters should be alphanumeric");
    }
}