package com.lcwd.user.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {
	
	@DeleteMapping("/rating/deleteRatingById/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") String ratingId);

}
