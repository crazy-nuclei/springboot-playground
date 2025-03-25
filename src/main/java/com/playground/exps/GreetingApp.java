package com.playground.exps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GreetingApp {
    public static void main(String[] args) {
        SpringApplication.run(GreetingApp.class, args);
    }
}

@RestController
@RequestMapping("/greet")
class GreetController {

    @GetMapping
    String greet(@RequestParam( name = "name", defaultValue = "Guest") String name) {
        return "Hello, " + name;
    }
}
