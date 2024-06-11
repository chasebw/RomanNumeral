package com.chase.app.roman.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RomanNumeralController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

}
