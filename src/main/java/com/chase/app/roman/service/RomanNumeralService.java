package com.chase.app.roman.service;

import org.springframework.stereotype.Service;


@Service
public class RomanNumeralService implements IRomanNumeralService {
    private final String[] romanNumeralArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private final int[] intForRomanArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final int MAX_ROMAN_INT = 3999;

    public String calculateRomanNumeralFromInt(int number) {
        if (number < 1 || number > MAX_ROMAN_INT ) {
            throw new IllegalArgumentException(
                    "Invalid number: " + number + " valid range from 1 to " + MAX_ROMAN_INT);
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(number > 0) {
            while(number >= intForRomanArr[i]) {
                number -= intForRomanArr[i];
                sb.append(romanNumeralArr[i]);
            }
            i++;
        }
        return sb.toString();
    }
}
