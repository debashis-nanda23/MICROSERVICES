package com.lcwd.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entites.User;
import com.lcwd.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User userNew=userService.saveUser(user);
		return  ResponseEntity.status(HttpStatus.CREATED).body(userNew);
	}
	
	int retryCount=1;
	
	//get single user
	@GetMapping("/getSingleUser/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod ="ratingHotelFallback" )
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
	 System.out.println("Retru Count is "+retryCount);
	  retryCount++;
	  
		User user=userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	
	//creating fallback method for circuit breaker
	//return type pof main method abd circuit breaker method should be same
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
      
		User user=  User.builder().email("dummy@gmail.com").
        name("dummy").
        about("This user is created beacuse some servcies are down").
        userId("123456").build();
		return new ResponseEntity<>(user,HttpStatus.OK);		
	}
	
	//get All User
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> userList=userService.getAllUsers();
		return ResponseEntity.ok(userList);
	}
	
	
	@DeleteMapping("/deleteUserByUserId/{userId}")
	public ResponseEntity<User> deleteUserByUserId(@PathVariable("userId") String userId){
	    User user=userService.deleteUser(userId);
	    return ResponseEntity.ok(user);

	}
	
	
	
}
