package com.example.springboottest.usertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;

import com.example.springboottest.user.User;
import com.example.springboottest.user.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


// ------------------FILE OVERVIEW----------------------------
//
//   Acceptance Tests: no mocking, rest client directly queries the end-point 
//



//---------------- ANNOTATIONS IN THIS FILE ---------------------
//
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT),
// @BeforeEach, @Test, @LocalServerPort
//


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTests {

    @LocalServerPort
    int randomServerPort;
    private RestTemplate restTemplate;
    private String url;

    @Autowired
    UserRepository userRepository;



    @BeforeEach
    void setUp() {
       // run the mongodb service
       userRepository.deleteAll();
       List<User> users = Arrays.asList( new User(  "Danile", "India", "1234a", 31),
                                         new User("Huy", "UK", "5678b", 35),
                                         new User("John", "India", "7877q", 38)) ;
       users.stream().forEach(u -> userRepository.save(u) );
        

       // create client object which makes http calls
       restTemplate = new RestTemplate();
       url = "http://localhost:" + randomServerPort + "/users";
        
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonResponse =  "[{\"name\":\"Danile\",\"address\":\"India\",\"ssid\":\"1234a\",\"age\":31}," +
                                "{\"name\":\"Huy\",\"address\":\"UK\",\"ssid\":\"5678b\",\"age\":35}," +  
                                "{\"name\":\"John\",\"address\":\"India\",\"ssid\":\"7877q\",\"age\":38}]";
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(jsonResponse, responseEntity.getBody());
    }


    
    @Test
    void shouldGetUsersByAddress() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "/India", String.class);
        String jsonResponse =  "[{\"name\":\"Danile\",\"address\":\"India\",\"ssid\":\"1234a\",\"age\":31}," +
                                "{\"name\":\"John\",\"address\":\"India\",\"ssid\":\"7877q\",\"age\":38}]";
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(jsonResponse, responseEntity.getBody());
    }


    @Test
    void shouldAddUser() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, new User("Helen", "US", "7612w", 31), String.class );
        String jsonResponse =  "{\"name\":\"Helen\",\"address\":\"US\",\"ssid\":\"7612w\",\"age\":31}";
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(jsonResponse, responseEntity.getBody());
    }

    @Test
    void shouldDeleteUser() throws Exception {

        User john = new User("John", "India", "7877q", 38) ;
        String jsonResponse =  "[{\"name\":\"Danile\",\"address\":\"India\",\"ssid\":\"1234a\",\"age\":31}," + // user: Danile
                                "{\"name\":\"Huy\",\"address\":\"UK\",\"ssid\":\"5678b\",\"age\":35}]";        // user: Huy
        
        restTemplate.delete(url + "/{ssid}", john.getSsid());     
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);      
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(jsonResponse, responseEntity.getBody());
    }
    
}

