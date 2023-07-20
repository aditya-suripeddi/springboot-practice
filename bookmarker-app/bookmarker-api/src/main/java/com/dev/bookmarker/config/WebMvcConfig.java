package com.dev.bookmarker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // CORS: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // For client reqs on endpoints with prefix /api/**
                .allowedMethods("*") // Allow all HTTP Methods
                .allowedHeaders("*")  // Allow all headers
                .allowedOriginPatterns("*"); // if your api allows calls only from a particular domain
                                             // you can mention that in allowed Origin patterns method
    }
}
