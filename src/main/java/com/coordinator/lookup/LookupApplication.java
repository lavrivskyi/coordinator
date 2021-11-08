package com.coordinator.lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@SpringBootApplication
@RestController
public class LookupApplication {

    @RequestMapping("/")
    public String home() {
        return "Coordinator app...";
    }

    public static void main(String[] args) {
        SpringApplication.run(LookupApplication.class, args);
    }
}
