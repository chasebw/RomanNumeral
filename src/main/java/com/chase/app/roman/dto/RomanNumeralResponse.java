package com.chase.app.roman.dto;

public class RomanNumeralResponse {
    public RomanNumeralResponse(int number, String romanNumeral) {
        this.number = number;
        this.romanNumeral = romanNumeral;
    }

    int number;
    String romanNumeral;

    public String getRomanNumeral() {
        return romanNumeral;
    }

    public void setRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
