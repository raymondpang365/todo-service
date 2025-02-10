package com.raymondpang365.config;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthTestEndpoint {
    @GetMapping("/")
    public String getIndex() {
        return "Success";
    }
}
