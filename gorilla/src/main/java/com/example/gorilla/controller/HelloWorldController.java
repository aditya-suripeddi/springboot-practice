package com.example.gorilla.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${some.other.service}")
    private String someServiceUrl;

    @Value("${api.gateway.service}")
    private String gatewayUrl;

    @RequestMapping("/hello")
    public String hello() {
        return String.format("Gorilla App with someServiceUrl: %s, gatewayUrl: %s", someServiceUrl, gatewayUrl);
    }
}
