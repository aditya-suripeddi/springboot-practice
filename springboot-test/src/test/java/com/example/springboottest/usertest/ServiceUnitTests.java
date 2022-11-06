package com.example.springboottest.usertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.springboottest.user.UserRepository;
import com.example.springboottest.user.User;
import com.example.springboottest.user.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;     


//----------------- FILE OVERVIEW --------------------
//
//  unit-test service layer logic 
//  by mocking the repository
//


//------------- REFERENCE ----------------------
//
// https://github.com/Java-Techie-jt/spring-boot-mockito
//


// ----------- ANNOTATIONS IN THIS FILE----------------
//
//  @SpringBootTest, @AutoConfigureMvc,
//  @MockBean, @Autowired, @Test
//


@SpringBootTest
@AutoConfigureMockMvc
public class ServiceUnitTests {

    @Autowired // note: we autowire service and mock repository
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test 
    public void getUsersTest() {

        List<User> users = Stream.of(new User("Danile", "USA", "1234a", 31),
                                     new User("Huy", "UK", "5678b", 35)).collect(Collectors.toList());

        when(repository.findAll()).thenReturn(users);
		assertEquals(2, service.getUsers().size());  
    }

    @Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User("Danile", "USA", "1234a", 31)).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}


    @Test
	public void getUserbySSidTest() {
		User danile  = new User("Danile", "USA", "1234a", 31);
		
		when(repository.findBySsid(danile.getSsid())).thenReturn(danile);
		assertEquals(danile, service.getUserBySsid(danile.getSsid()) );
	}

	@Test
	public void saveUserTest() {
		User user = new User("Pranya", "Pune", "7877q", 33);
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User("Pranya", "Pune", "7877q", 33);
		assertEquals(user, service.deleteUser(user));
        verify(repository, times(1)).delete(user);;
	}

}