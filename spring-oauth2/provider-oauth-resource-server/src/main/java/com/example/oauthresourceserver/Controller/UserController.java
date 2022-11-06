package com.example.oauthresourceserver.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    Map<String, String> userAddressDB = Map.of("venkat@gmail.com", "Bangalore, India",
                                               "vinayaka@gmail.com", "Hyderabad, India");

    @GetMapping("/provider/api/address")
    public String getUsers(@AuthenticationPrincipal Jwt jwt) {
        String subject = jwt.getSubject();
        return String.format("Address of %s is %s", subject, userAddressDB.get(subject));
    }
}
