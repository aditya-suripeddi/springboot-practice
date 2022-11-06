package com.example.springboottest.usertest;



import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.example.springboottest.user.User;
import com.example.springboottest.user.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


//------------------ FILE OVERVIEW -------------------------
//
//   integration test: 
//          we are testing `our code/business-logic 
//          along with spring-boot code`
//
//    in unit-test we checked by calling the methods of controller
//    here we compose the request,  send to the end-point 
//    and check the http-response from hitting the end-point
//
//    here we are testing the end-points of our server, 
//    we are testing if spring-boot routes to  right controller and method,
//    and if correct http-status codes and json-responses are returned
//



//---------------------- @TODOs ---------------------------------
//
//     - write test cases for DELETE /user who is not in database (ResourceNotFoundException) 
//




//--------------------- REFERENCES -------------------------------    
//
//  unit, integration, acceptance testing spring-boot: 
//          https://github.com/kriscfoster/spring-boot-testing-pyramid/
//




//------------------------------ ANNOTATIONS IN THIS FILE -----------------------------
//
//   @WebMvcTest
//  
//   @Autowired,  @MockBean, @Test
// 



@WebMvcTest
class IntegrationTests {

    @Autowired
	private MockMvc mockMvc; 
	
	@MockBean 
	private UserService userService;

	@Test
	void getAllUsersTest() throws Exception {
		
   	    List<User> users = Arrays.asList(new User("Danile", "USA", "1234a", 31),
	                                     new User("Huy", "UK", "5678b", 35)) ;

	    when(userService.getUsers()).thenReturn(users) ;
	
		String  jsonResponse = 	   "[{\"name\":\"Danile\",\"address\":\"USA\",\"ssid\":\"1234a\",\"age\":31}," +  // user: Danile
								    "{\"name\":\"Huy\",\"address\":\"UK\",\"ssid\":\"5678b\",\"age\":35}]";       // user: Huy

	    mockMvc.perform( get("/users") )
	           .andDo( print() )
		  	   .andExpect( status().isOk() )
			   .andExpect( content().string(equalTo(jsonResponse)));      

	     verify(userService, times(1)).getUsers(); // verify that getUsers() method is called once in userService
	}

	@Test
	void getUsersByAddress() throws Exception {
		
   	    List<User> usersFromIndia = Arrays.asList(new User("Danile", "India", "1234a",  31),
	                                     new User("Huy", "India", "5678b",  35)) ;

	    when(userService.getUserbyAddress("India")).thenReturn(usersFromIndia) ;
	
		String  jsonResponse = "[{\"name\":\"Danile\",\"address\":\"India\",\"ssid\":\"1234a\",\"age\":31},"+   // user: Danile
		                       "{\"name\":\"Huy\",\"address\":\"India\",\"ssid\":\"5678b\",\"age\":35}]" ;     // user: Huy


	    mockMvc.perform( get("/users/India") )
	           .andDo( print() )
		  	   .andExpect( status().isOk() )
			   .andExpect( content().string(equalTo(jsonResponse)));      

	     verify(userService, times(1)).getUserbyAddress("India"); // verify that getUsersByAddress() method is called once in userService
	}


	@Test
	void addUser() throws Exception {
		
		  User john =  new User("John", "India", "7877q", 32) ; 
	      when(userService.addUser(any(User.class))).thenReturn(john);


     	  String  jsonResponse = "{\"name\":\"John\",\"address\":\"India\",\"ssid\":\"7877q\",\"age\":32}" ;

	      mockMvc.perform( post("/users")
		                  .contentType(MediaType.APPLICATION_JSON)
						  .accept(MediaType.APPLICATION_JSON)
		                  .content("{\"name\":\"John\",\"address\":\"India\",\"age\":32}"))
						  .andDo( print() )
						  .andExpect(status().isOk())
						  .andExpect( content().string(equalTo(jsonResponse)) );
			 

	      verify(userService, times(1)).addUser(any(User.class)); // verify that addUser() method is called on userService
 }

 @Test
 void deleteUser() throws Exception { // assuming: user requested for deletion exists in the database
	 
      User john =  new User("John", "US", "7877q", 32) ; 
	  String  jsonResponse = "{\"name\":\"John\",\"address\":\"US\",\"ssid\":\"7877q\",\"age\":32}" ;
	  
	  when(userService.getUserBySsid(john.getSsid())).thenReturn(john);
      when(userService.deleteUser(john)).thenReturn(john);

	  mockMvc.perform( delete("/users/" + john.getSsid())
					   .contentType(MediaType.APPLICATION_JSON)
					   .accept(MediaType.APPLICATION_JSON))
					   .andDo( print() )
					   .andExpect(status().isOk())
					   .andExpect( content().string(equalTo(jsonResponse)) );
		  

	   verify(userService, times(1)).deleteUser(any(User.class)); // verify that deleteUser() method is called once inuserService
}

}
