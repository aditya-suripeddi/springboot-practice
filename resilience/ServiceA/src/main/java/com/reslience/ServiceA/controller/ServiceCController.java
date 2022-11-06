package com.reslience.ServiceA.controller;

import com.reslience.ServiceA.clients.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/c")
public class ServiceCController {

    @Autowired
    private EmailClient emailClient;
    private static String BASE_URL = "http://localhost:8081";

    @GetMapping
    public String serviceC() {
        String url = BASE_URL + "/b";
        emailClient.sendEmail(url);
        return " from Service A";
    }
}