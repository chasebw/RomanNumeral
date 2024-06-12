package com.chase.app.roman.dto;

public class RomanNumeralResponse {

    private long number;
    private String romanNumeral;

    public RomanNumeralResponse(long number, String romanNumeral) {
        this.number = number;
        this.romanNumeral = romanNumeral;
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }
    public void setRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }
    public long getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
