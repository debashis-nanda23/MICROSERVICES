package com.debashis.rating.controller;

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

import com.debashis.rating.entities.Rating;
import com.debashis.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/createRating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating ratingNew=ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingNew);
	}
	
	@GetMapping("/getAllRating")
	public ResponseEntity<List<Rating>> getAllRating(){
		List<Rating> ratingList=ratingService.getAllRating();
		return ResponseEntity.ok(ratingList);
	}
	
	@GetMapping("/getRatingByUserId/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}
	
	@GetMapping("/getRatingByHotelId/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
	
	@DeleteMapping("/deleteRatingById/{ratingId}")
	public void deleteRatingById(@PathVariable("ratingId") String ratingId) {
		ratingService.deleteRatingById(ratingId);
	}
}


