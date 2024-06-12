package com.chase.app.roman.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestRomanNumeralService {

    String cOverline = "\u0305";
    String dOverline = cOverline + cOverline;

    @Test
    public void testRomanNumeralMin() {
        long input = 1;
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
    public void testBasicCasesSmall(long input, String expected) {
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
    public void testSubtractiveCasesSmall(long input, String expected) {
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
    public void testCompoundCasesSmall(long input, String expected) {
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);

        assertEquals(result, expected);
    }
    //    look into csv source

    @Test
    public void testSingleLargeNumber() {
        RomanNumeralService r = new RomanNumeralService();
        long input = 1_999_000_000L;

        String result = r.calculateRomanNumeralFromInt(input);

        String expected = "M" + dOverline + "C" + dOverline +  "M" +  dOverline +
                "X" + dOverline + "C" + dOverline + "I" + dOverline + "X" + dOverline;
        assertEquals(result, expected);
    }


    @Test
    public void testRomanNumeralMax() {
        long input = 2_200_000_000L;
        RomanNumeralService r = new RomanNumeralService();

        String result = r.calculateRomanNumeralFromInt(input);


        String mdOverline = "M" + dOverline;
        String cdOverline = "C" + dOverline;
        String expected = mdOverline + mdOverline + cdOverline + cdOverline;
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L, 2_200_000_001L})
    public void testRomanNumeralIntOutOfRangeThrowIllegalArgumentException(long parameterInt) {
        RomanNumeralService r = new RomanNumeralService();
        IllegalArgumentException ex = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> r.calculateRomanNumeralFromInt(parameterInt)
        );

        assertTrue(ex.getMessage().contains("Invalid number:"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_roman_numerals.csv", numLinesToSkip = 1)
    public void run_csv_test_file(long number, String expected) {
        RomanNumeralService r = new RomanNumeralService();
        String result = r.calculateRomanNumeralFromInt(number);
        assertEquals(result, expected);
    }
}
