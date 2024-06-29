package com.chase.app.roman.dto;

public class RomanNumeralResponse {

    private long query;
    private String romanNumeral;

    public RomanNumeralResponse(long query, String romanNumeral) {
        this.query = query;
        this.romanNumeral = romanNumeral;
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }
    public void setRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }
    public long getQuery() {
        return query;
    }
    public void setQuery(int query) {
        this.query = query;
    }
}
