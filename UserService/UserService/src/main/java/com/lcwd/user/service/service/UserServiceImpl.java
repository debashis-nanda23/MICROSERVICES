package com.lcwd.user.service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entites.Hotel;
import com.lcwd.user.service.entites.Rating;
import com.lcwd.user.service.entites.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.HotelService;
import com.lcwd.user.service.external.RatingService;
import com.lcwd.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on the server"));
	   
		//fetch rating of the above user
		Rating[] forObject= restTemplate.getForObject("http://RATINGSERVICE/rating/getRatingByUserId/"+userId,Rating[].class);
	
		List<Rating> ratings= Arrays.stream(forObject).toList();
		
		List<Rating> ratingList=ratings.stream().map(rating->{
			/*
			 * ResponseEntity<Hotel> forEntity=
			 * restTemplate.getForEntity("http://HOTELSEVICE/hotel/getHotelById/"+rating.
			 * getHotelId(), Hotel.class); Hotel hotel= forEntity.getBody();
			 */
		        Hotel hotel=hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
		user.setRatings(ratingList);
	
		return user;
	}

	@Override
	public User deleteUser(String userId) {
		User user=getUser(userId);
		userRepository.deleteById(userId);
		
		//delete rating associated with user
		user.getRatings().stream().forEach(rating->{
			System.out.println(rating.getRatingId());
			ratingService.deleteRating(rating.getRatingId());
		});
		
		
		
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
