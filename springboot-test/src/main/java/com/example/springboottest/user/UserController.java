package com.example.springboottest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/users")
    public User saveUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/users/{address}")
    public List<User> findUserByAddress(@PathVariable String address) {
        return service.getUserbyAddress(address);
    }

    @DeleteMapping(value = "/users/{ssid}")
    public User removeUser(@PathVariable String ssid) {
        User user = service.getUserBySsid(ssid);
        return service.deleteUser(user);
    }
}



