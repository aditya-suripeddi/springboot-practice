package com.practice.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class HelloController {

    @Autowired
    private WebClient webClient;


    @GetMapping("/api/hello")
    public String hello(Principal principal) {
        return "Hi, " + principal.getName() + " you have reached /api/hello  on oauth-client-app";
    }

    // call the resource server via authorization server and fetch details
    // authorized by resource owner (user of oauth-client-app)

    @GetMapping("/api/profile")
    public String users(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
            OAuth2AuthorizedClient client) {


        return this.webClient
                .get()
                .uri("http://provider-oauth-resource-server:8090/provider/api/address")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
