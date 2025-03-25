package com.playground.exps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class JokeApp {
    public static void main(String[] args) {
        SpringApplication.run(JokeApp.class, args);
    }
}

@RestController
@RequestMapping("/joke")
class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public String getJoke() {
        return jokeService.getRandomJoke();
    }
}

@Service
class JokeService {
    private static final List<String> JOKES = List.of(
            "Why do Java developers wear glasses? Because they don’t C#!",
            "How many programmers does it take to change a light bulb? None, it's a hardware problem!",
            "Why do programmers prefer dark mode? Because light attracts bugs!"
    );

    public String getRandomJoke() {
        return JOKES.get(new Random().nextInt(JOKES.size()));
    }
}

