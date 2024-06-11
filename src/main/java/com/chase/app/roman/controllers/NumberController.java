package com.chase.app.roman.controllers;

import com.chase.app.roman.dto.RomanNumeralResponse;
import com.chase.app.roman.exceptions.InvalidInputException;
import com.chase.app.roman.service.IRomanNumeralService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/romannumeral")
    public ResponseEntity<RomanNumeralResponse> romanNumeral(
            @RequestParam(name = "query") int number) throws Exception {
        logger.debug("Requested - Roman Numeral Query: {}", number);

        if (number < 1 || number > 3999) {
            throw new InvalidInputException(
                    String.format("Invalid Input: '%s' number out of range [1,3999].", number)
            );
        }

        String romanNumeral = romanNumeralService.calculateRomanNumeralFromInt(number);
        logger.debug("Response - Roman Numeral Result: {}", romanNumeral);

        return ResponseEntity
                .ok(new RomanNumeralResponse(number, romanNumeral));
    }
}
