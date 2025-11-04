package com.debashis.rating.service;

import java.util.List;

import com.debashis.rating.entities.Rating;

public interface RatingService {

	public Rating createRating(Rating rating);
	public List<Rating> getAllRating();	
	public List<Rating> getRatingByUserId(String userId);
	public List<Rating> getRatingByHotelId(String hotelId);
	public void deleteRatingById(String ratingId);
	
}
