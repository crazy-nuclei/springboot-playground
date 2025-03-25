package com.playground.exps;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class LoggerApp {
    public static void main(String[] args) {
        SpringApplication.run(LoggerApp.class, args);
    }
}

@RestController
class LoggerController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @GetMapping("/log")
    public String logMessage(@RequestParam(name = "message", defaultValue = "Hello, Logger!") String message) {
        logger.info("INFO: Received request with message: {}", message);
        logger.debug("DEBUG: Debugging request with message: {}", message);
        logger.error("ERROR: Simulating an error log with message: {}", message);

        return "Logged message: " + message;
    }
}

