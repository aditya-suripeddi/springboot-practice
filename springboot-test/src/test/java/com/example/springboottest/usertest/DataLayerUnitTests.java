package com.example.springboottest.usertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.example.springboottest.user.User;
import com.example.springboottest.user.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// ------------------FILE OVERVIEW----------------------------
//
//   Unit tests for data layer / persistence layer
//

//---------------------REFERENCES--------------------------
//
//  gs-accessing-data-mongodb (official source):
//   https://github.com/spring-guides/gs-accessing-data-mongodb/blob/main/complete/src/test/java/com/example/accessingdatamongodb/CustomerRepositoryTests.java
//

@SpringBootTest
class DataLayerUnitTests {

    @Autowired
    UserRepository userRepository;

    List<User> users = Arrays.asList( new User( "Danile", "India", "1234a", 31),
                                      new User("Huy", "UK", "5678b", 35),
                                      new User("John", "India", "7877q", 38)) ;


    @BeforeEach
    void setUp() {
       // run the mongodb service
       userRepository.deleteAll();
       users.stream().forEach(u -> userRepository.save(u) );    
    }


    @Test
    void shouldReadUsersFromDB() throws Exception {
        assertEquals(users.size(), userRepository.findAll().size()) ;
    }
    
    @Test
    void ShouldFilterUsersByAddressFromDB() throws Exception {

        Stream<User> usersFromIndia = users.stream().filter(u -> u.getAddress().compareTo("India") == 0) ;
        Stream<User> usersFromIndiaInDB = userRepository.findAll().stream().filter( u -> u.getAddress().compareTo("India") == 0);
        assertEquals( usersFromIndia.count(), usersFromIndiaInDB.count()) ;
    }


    @Test
    void ShouldAddUserToDB() throws Exception {
      userRepository.save(new User("Pranya", "Pune", "7877q", 33) );
      assertEquals( userRepository.findAll().size(), users.size() + 1) ;
    }

    @Test
    void shouldDeleteUserFromDB() throws Exception {
        User danile = new User( "Danile", "India", "1234a", 31) ;
        userRepository.delete( userRepository.findBySsid(danile.getSsid()) ) ;
        assertEquals( userRepository.findAll().size(), users.size() - 1) ;
    }
    
}

