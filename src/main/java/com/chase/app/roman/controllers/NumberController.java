package com.chase.app.roman.controllers;

import com.chase.app.roman.dto.RomanNumeralResponse;
import com.chase.app.roman.exceptions.InvalidInputException;
import com.chase.app.roman.service.IRomanNumeralService;
import com.chase.app.roman.service.RomanNumeralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {

    private final IRomanNumeralService romanNumeralService;
    private static final Logger logger = LoggerFactory.getLogger(NumberController.class);


    public NumberController(IRomanNumeralService romanNumeralService) {
        this.romanNumeralService = romanNumeralService;
    }

    /**
     * Endpoint for handling roman numeral query
     *
     * @param number integer value to get corresponding romanNumeral for.
     * @return a {@link ResponseEntity} of {@link RomanNumeralResponse} that holds
     * the provided integer from the number `query` and calculated RomanNumeral for that integer.
     */
    @GetMapping("/romannumeral")
    public ResponseEntity<RomanNumeralResponse> romanNumeral(
            @RequestParam(name = "query") long number
    ) throws Exception {
        logger.debug("Requested - Roman Numeral Query: {}", number);


        if (number < RomanNumeralService.MIN_ROMAN_INT || number > RomanNumeralService.MAX_ROMAN_INT) {
            throw new InvalidInputException(
                    String.format("Invalid Input: '%d' number out of range [%d, %d].",
                            number,
                            RomanNumeralService.MIN_ROMAN_INT,
                            RomanNumeralService.MAX_ROMAN_INT)
            );
        }

        String romanNumeral = romanNumeralService.calculateRomanNumeralFromInt(number);
        logger.debug("Response - Roman Numeral Result: {}", romanNumeral);

        return ResponseEntity
                .ok(new RomanNumeralResponse(number, romanNumeral));
    }
}
