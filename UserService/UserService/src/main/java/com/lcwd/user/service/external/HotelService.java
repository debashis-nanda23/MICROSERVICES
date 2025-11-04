package com.lcwd.user.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entites.Hotel;

@FeignClient(name="HOTELSEVICE")
public interface HotelService {

	@GetMapping("/hotel/getHotelById/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
	
}
