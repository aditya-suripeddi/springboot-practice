package com.example.springboottest.user;

import static java.lang.System.out;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public User addUser(User user) {
    return repository.save(user);
  }

  public List<User> getUsers() {
    List<User> users = repository.findAll();
    out.println("Getting data from DB : " + users);
    return users;
  }

  public List<User> getUserbyAddress(String address) {
    return repository.findByAddress(address);
  }

 
  public User getUserBySsid(String ssid) {
    return repository.findBySsid(ssid);
  }

  public User deleteUser(User user) {
    repository.delete(user) ;
    return user;
  }

}