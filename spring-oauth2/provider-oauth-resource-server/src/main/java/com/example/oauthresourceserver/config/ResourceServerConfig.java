package com.example.oauthresourceserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// as name indicates configure securityFilterChain with Oauth2 resource server
@EnableWebSecurity(debug = true)
public class ResourceServerConfig {


    //  all /api/** requests must be authorized with SCOPE: api_read

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .mvcMatchers("/api/**")
                .access("hasAuthority('SCOPE_api.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

}
