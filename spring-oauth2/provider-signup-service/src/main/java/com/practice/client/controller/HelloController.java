package com.practice.client.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/provider/api/hello")
    public String hello(Principal principal) {
        return "Hi, " + principal.getName() + " you have reached /api/hello on provider";
    }

}
