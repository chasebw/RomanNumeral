package com.chase.app.roman.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidInput(InvalidInputException ex) {
        logger.error("error: {}", ex.getMessage(), ex);
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMisMatchException(Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        logger.error("error: {}", ex.getMessage(), ex);
        errorBody.put("error", "Invalid Input received: wrong type provided");
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingNumberException(Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        logger.error("error: {}", ex.getMessage(), ex);
        errorBody.put("error", String.format("Invalid Input missing required parameter: %s", ex.getMessage()));
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
