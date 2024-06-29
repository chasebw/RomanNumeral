package com.chase.app.roman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RomanNumeralService implements IRomanNumeralService {
    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralService.class);
    private static final String C_OVERLINE = "\u0305"; // combing overline utf-8 char
    private static final String D_OVERLINE = "\u033F"; // double overline
    private static final String[] ROMAN_FOR_INT_ARR = {
            applyOverline("M",  D_OVERLINE),  // 1,000,000,000
            applyOverline("CM", D_OVERLINE),  // 900,000,000
            applyOverline("D",  D_OVERLINE),  // 500,000,000
            applyOverline("CD", D_OVERLINE),  // 400,000,000
            applyOverline("C",  D_OVERLINE),  // 100,000,000
            applyOverline("XC", D_OVERLINE),  // 90,000,000
            applyOverline("L",  D_OVERLINE),  // 50,000,000
            applyOverline("XL", D_OVERLINE),  // 40,000,000
            applyOverline("X",  D_OVERLINE),  // 10,000,000
            applyOverline("IX", D_OVERLINE),  // 9,000,000
            applyOverline("V",  D_OVERLINE),  // 5,000,000
            applyOverline("IV", D_OVERLINE),  // 4,000,000
            // **
            applyOverline("M",  C_OVERLINE),  // 1,000,000
            applyOverline("CM", C_OVERLINE),  // 900,000
            applyOverline("D",  C_OVERLINE),  // 500,000
            applyOverline("CD", C_OVERLINE),  // 400,000
            applyOverline("C",  C_OVERLINE),  // 100,000
            applyOverline("XC", C_OVERLINE),  // 90,000
            applyOverline("L" , C_OVERLINE),  // 50,000
            applyOverline("XL", C_OVERLINE),  // 40,000
            applyOverline("X",  C_OVERLINE),  // 10,000
            applyOverline("IX", C_OVERLINE),  // 9,000
            applyOverline("V",  C_OVERLINE),  // 5,000
            applyOverline("IV", C_OVERLINE),  // 4,000
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
    private static final long[] INT_FOR_ROMAN_ARR = {
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
    public static final long MIN_ROMAN_INT = 1L;
    public static final long MAX_ROMAN_INT = 2_200_000_000L;

    /**
     * used to calculate the Roman numeral for a particular integer
     *
     * @param number integer value to convert to a roman numeral.
     * @return string for corresponding Roman Numeral for int.
     * @throws IllegalArgumentException if number is not in valid range 1 to 2 billion 200 Million.
     */
    @Cacheable("roman-cache")
    public String calculateRomanNumeralFromInt(long number) {
        if (number < MIN_ROMAN_INT || number > MAX_ROMAN_INT ) {
            throw new IllegalArgumentException(
                    "Invalid number: " + number + " valid range from 1 to " + MAX_ROMAN_INT);
        }
        logger.debug("Calculating Roman numeral for number: {}", number);

        StringBuilder sb = new StringBuilder();
        for(int i= 0; number > 0; i++) {
            while(number >= INT_FOR_ROMAN_ARR[i]) {
                number -= INT_FOR_ROMAN_ARR[i];
                sb.append(ROMAN_FOR_INT_ARR[i]);
            }
        }
        return sb.toString();
    }

    /**
     * private utility method for appending overline(hats) consistently
     * onto the roman numerals.
     *
     * @param romanNumeral string to apply overline to.
     * @param overline overline character to use to apply after each character.
     * @return A string with the overline(hat) applied to all the romanNumeral characters in string.
     */
    private static String applyOverline(String romanNumeral, String overline) {
        char[] result = new char[romanNumeral.length() * 2];
        char overlineChar = overline.charAt(0);
        for (int i = 0, j = 0; i < romanNumeral.length(); i++) {
            result[j++] = romanNumeral.charAt(i);
            result[j++] = overlineChar;
        }
        return new String(result);
    }
}
