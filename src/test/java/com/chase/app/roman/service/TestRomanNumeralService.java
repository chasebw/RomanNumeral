package com.chase.app.roman.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestRomanNumeralService {

    @Test
    public void testRomanNumeral1() {
        int input = 1;
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        String expected = "I";
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "1, I",
            "5, V",
            "10, X",
            "50, L",
            "100, C",
            "500, D",
            "1000, M",
    })
    public void testBasicCases(int input, String expected) {
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        assertEquals(result, expected);
    }


    @ParameterizedTest
    @CsvSource({
            "44, XLIV",
            "99, XCIX",
            "444, CDXLIV",
            "999, CMXCIX",
            "2444, MMCDXLIV",
            "3434, MMMCDXXXIV"
    })
    public void testSubtractiveCases(int input, String expected) {
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "58, LVIII",
            "164, CLXIV",
            "372, CCCLXXII",
            "1994, MCMXCIV",
            "2421, MMCDXXI",
    })
    public void testCompoundCases(int input, String expected) {
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        assertEquals(result, expected);
    }


    @Test
    public void testRomanNumeral3999() {
        int input = 3999;
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        String expected = "MMMCMXCIX";
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000})
    public void testRomanNumeralIntOutOfRangeThrowIllegalArgumentException(int parameterInt) {
        RomanNumeralService r = new RomanNumeralService();
        IllegalArgumentException ex = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> r.calculateRomanNumeralFromInt(parameterInt)
        );

        assertTrue(ex.getMessage().contains("Invalid number:"));
    }
}
