package com.chase.app.roman.controllers;

import com.chase.app.roman.service.RomanNumeralService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberController.class)
public class TestNumberController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RomanNumeralService romanNumeralServiceMock;


    @Test
    public void testRomanNumeralQueryWhenSuccessReturns200WithResponse() throws Exception {
        int input = 1;
        String url = String.format("/romannumeral?query=%d", input);
        when(romanNumeralServiceMock.calculateRomanNumeralFromInt(1)).thenReturn("I");

        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.romanNumeral").value("I"));

    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000})
    public void testRomanNumeralQueryWhenIntOutOfRangeReturnsBadRequestWithError(int parameterInt) throws Exception {
        String url = String.format("/romannumeral?query=%d", parameterInt);
        when(romanNumeralServiceMock.calculateRomanNumeralFromInt(1)).thenReturn("I");
        String expected_error = String.format("Invalid Input: '%d' number out of range [1,3999].", parameterInt);

        mvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error")
                        .value(expected_error));
    }

    @Test
    public void testWhenNoQueryValueReturnsBadRequestWithError() throws Exception {
        String url = "/romannumeral";
        String expected_error = "Invalid Input: required parameter missing 'query'.";

        mvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error")
                        .value(expected_error));
    }

    @Test
    public void testWhenInvalidUrlReturns404() throws Exception {
        String url = "/NOT-VALID-URL-PATH";

        mvc.perform(get(url))
                .andExpect(status().isNotFound());
    }
}
