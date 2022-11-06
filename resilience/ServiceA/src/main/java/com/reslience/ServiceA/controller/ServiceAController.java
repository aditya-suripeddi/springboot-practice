package com.reslience.ServiceA.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;


@RestController
@RequestMapping("/a")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081";

    private static final String SERVICE_A = "serviceA";

    int count = 1;

    @GetMapping
    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallBack")
    //@Retry(name = SERVICE_A)
    //@RateLimiter(name = SERVICE_A)
    public String serviceA() {
         String url = BASE_URL + "/b";

         // uncommment the line below when @Retry is uncommented
         // out.println("Retry method called " +  (count++) + " times");
         String response = restTemplate.getForObject(url,String.class);
         return response  +  " from Service A";
    }


    // the fall back method should:
    //    a. have same return type as annotated method - serviceA()
    //    b. take Exception as argument
    //    c. be defined in same class as annotated method [CHECK]
    public String serviceAFallBack(Exception ex) {
        return " this is fallback method of serviceA" ;
    }
}
