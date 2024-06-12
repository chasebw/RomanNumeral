package com.chase.app.roman.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);


    /**
     * Custom handling error cases like Not Found or internal server error
     *
     * @param request received from client
     * @return an appropriate ResponseEntity Error for the custom case
     */
    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object path = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                logger.error("404 Not Found: {}", path);
                response.put("error", "Path Not Found: " + path);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                logger.error("error: {}", "500 - Internal Server Error");
                response.put("error", "Internal Server Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

        logger.error("error: {}", "400 - Unknown Error");
        response.put("error", "Bad Request: Unknown Error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
