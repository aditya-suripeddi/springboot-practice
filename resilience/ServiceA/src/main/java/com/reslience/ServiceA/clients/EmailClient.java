package com.reslience.ServiceA.clients;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;


@Component
public class EmailClient {

    @Autowired
    private RestTemplate restTemplate;

    private  static int count = 1 ;

    @Retry(name = "serviceA")
    @Async
    public void sendEmail(String url) {
        out.println("Retry method called " +  (count++) + " times");
        String response = restTemplate.getForObject(url, String.class);
        out.println("response: " + response);
    }
}
