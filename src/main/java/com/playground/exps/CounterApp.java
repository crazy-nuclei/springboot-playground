package com.playground.exps;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
public class CounterApp {
    public static void main(String[] args) {
        SpringApplication.run(CounterApp.class, args);
    }
}

@RestController
@RequestMapping("/count")
class CounterController {

    CounterService counterService;

    CounterController ( CounterService counterService ) {
        this.counterService = counterService;
    }

    @GetMapping
    Integer getCurrentCount() {
        return counterService.getCount();
    }
}

@Service
class CounterService {
    final private AtomicInteger counter = new AtomicInteger(0);

    public int getCount() {
        return counter.incrementAndGet();
    }
}
