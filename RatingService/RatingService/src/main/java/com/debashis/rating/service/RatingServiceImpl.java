package com.debashis.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debashis.rating.entities.Rating;
import com.debashis.rating.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) {
		rating.setRatingId(UUID.randomUUID().toString());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public void deleteRatingById(String ratingId) {
		ratingRepository.deleteById(ratingId);
	}

}
