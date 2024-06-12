package com.chase.app.roman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RomanNumeralService implements IRomanNumeralService {
    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralService.class);
    private static final String cOverline = "\u0305"; // combing overline utf-8 char
    private static final String dOverline = cOverline + cOverline; // double overline
    private static final String[] romanNumeralArr = {
            doubleOverline("M"),  // 1,000,000,000
            doubleOverline("CM"), // 900,000,000
            doubleOverline("D"),  // 500,000,000
            doubleOverline("CD"), // 400,000,000
            doubleOverline("C"),  // 100,000,000
            doubleOverline("XC"), // 90,000,000
            doubleOverline("L"),  // 50,000,000
            doubleOverline("XL"), // 40,000,000
            doubleOverline("X"),  // 10,000,000
            doubleOverline("IX"), // 9,000,000
            doubleOverline("V"),  // 5,000,000
            doubleOverline("IV"), // 4,000,000
            // **
            singleOverline("M"),  // 1,000,000
            singleOverline("CM"), // 900,000
            singleOverline("D"),  // 500,000
            singleOverline("CD"), // 400,000
            singleOverline("C"),  // 100,000
            singleOverline("XC"), // 90,000
            singleOverline("L"),  // 50,000
            singleOverline("XL"), // 40,000
            singleOverline("X"),  // 10,000
            singleOverline("IX"), // 9,000
            singleOverline("V"),  // 5,000
            singleOverline("IV"), // 4,000
            "M",  // 1000
            "CM", // 900
            "D",  // 500
            "CD", // 400
            "C",  // 100
            "XC", // 90
            "L",  // 50
            "XL", // 40
            "X",  // 10
            "IX", // 9
            "V",  // 5
            "IV", // 4
            "I"   // 1
    };
    private final long[] intForRomanArr = {
            1_000_000_000L,
            900_000_000L,
            500_000_000L,
            400_000_000L,
            100_000_000L,
            90_000_000L,
            50_000_000L,
            40_000_000L,
            10_000_000L,
            9_000_000L,
            5_000_000L,
            4_000_000L,
            1_000_000L,
            900_000L,
            500_000L,
            400_000L,
            100_000L,
            90_000L,
            50_000L,
            40_000L,
            10_000L,
            9_000L,
            5_000L,
            4_000L,
            1_000L,
            900L,
            500L,
            400L,
            100L,
            90L,
            50L,
            40L,
            10L,
            9L,
            5L,
            4L,
            1L
    };
    public static final long MIN_ROMAN_INT = 1;
    public static final long MAX_ROMAN_INT = 2_200_000_000L;


    /**
     * used to calculate the Roman numeral for a particular integer
     *
     * @param number integer value to get corresponding romanNumeral for.
     * @return a string for the corresponding romanNumeral for the integer provided
     */
    @Cacheable("roman-cache")
    public String calculateRomanNumeralFromInt(long number) {
        if (number < MIN_ROMAN_INT || number > MAX_ROMAN_INT ) {
            throw new IllegalArgumentException(
                    "Invalid number: " + number + " valid range from 1 to " + MAX_ROMAN_INT);
        }
        logger.debug("Calculating Number...");

        StringBuilder sb = new StringBuilder();
        for(int i= 0; number > 0; i++) {
            while(number >= intForRomanArr[i]) {
                number -= intForRomanArr[i];
                sb.append(romanNumeralArr[i]);
            }
        }
        return sb.toString();
    }

    /**
     * private utility method for appending overline(hats) consistently
     * onto the roman numerals. Recommended to use {@link #doubleOverline(String)}
     * or {@link #singleOverline(String)} instead of this method.
     *
     * @param romanNumeral string to apply overline to.
     * @param overline overline character to use to apply after each character.
     * @return A string with the overline(hat) applied to all the romanNumeral characters
     */
    private static String applyOverline(String romanNumeral, String overline) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < romanNumeral.length(); i++) {
            sb.append(romanNumeral.charAt(i));
            sb.append(overline);
        }
        return sb.toString();
    }

    private static String doubleOverline(String romanNumeral) {
        return applyOverline(romanNumeral, dOverline);
    }

    private static String singleOverline(String romanNumeral) {
        return applyOverline(romanNumeral, cOverline);
    }
}
