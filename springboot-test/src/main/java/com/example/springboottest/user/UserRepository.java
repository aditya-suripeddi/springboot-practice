package com.example.springboottest.user;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    List<User> findByAddress(String address);
    User findBySsid(String ssid);

   // uncommenting this creates problems in autowiring repository bean in AcceptanceTests: 
   //  User  deleteByUser(User user); 
}