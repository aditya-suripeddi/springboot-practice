package com.example.springboottest.usertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.example.springboottest.user.UserController;
import com.example.springboottest.user.User;
import com.example.springboottest.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


//----------------- FILE OVERVIEW -------------------
//
//  unit-testing the controller by mocking the service
//



//----------------- REFERENCE ------------------------
//
// @SpringBoot annotation:  https://howtodoinjava.com/spring-boot2/testing/springboottest-annotation/
//




//------------- ANNOTATIONS IN THIS FILE --------------
//
//   @SpringBootTest(classes = {SomeClass.class, SomeOtherClass.class} ):
// 
//           -  it [loads] whole application, but it is better to limit [Application Context]
//              only to a set of spring components that participate in [test scenario,
//
//           -  the classes attribute specifies the annotated classes to use for
//              [loading an ApplicationContext].
//
//
//   @BeforeEach, @Test,  @MockBean, @Autowired 
// 



@SpringBootTest(classes = {UserController.class, UserService.class}) // just load controller & service classes
public class ControllerUnitTests {
 
    @Autowired
    UserController userController ;
 
    @MockBean
    UserService userService;
  

    List<User> users = Arrays.asList(new User("Danile", "USA", "1234a", 31),
                                     new User("Huy", "India", "5678b", 35));


    User john =  new User("John", "India", "7877q", 32) ; 

    List<User> UsersFromIndia = Arrays.asList(john, users.get(1) ) ;


    @BeforeEach
    void setup() {                                                                                    
        when(userService.getUsers()).thenReturn(users);
        when(userService.addUser(john)).thenReturn(john);
        when(userService.getUserbyAddress("India")).thenReturn(UsersFromIndia) ;
    }


    @Test
    void shouldGetUsers() {
        assertEquals(users, userController.findAllUsers());
    }

    @Test
    void shouldSaveNewUser() {
        assertEquals(john, userController.saveUser(john) ) ;
    }

   
    @Test
    void shoudlGetUsersByAddress() {
        assertEquals(UsersFromIndia, userController.findUserByAddress("India")) ;
    }

   
    @Test
    void shouldDeleteUser() {
            when(userService.getUserBySsid(john.getSsid())).thenReturn(john);
            when(userService.deleteUser(john)).thenReturn(john);
            assertEquals(john, userController.removeUser(john.getSsid()) );
            verify(userService, times(1)).getUserBySsid(john.getSsid());
            verify(userService, times(1)).deleteUser(john);
    }
}
