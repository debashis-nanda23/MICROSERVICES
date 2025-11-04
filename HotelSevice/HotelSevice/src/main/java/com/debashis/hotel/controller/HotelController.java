package com.debashis.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.debashis.hotel.entities.Hotel;
import com.debashis.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/createHotel")
	public ResponseEntity<Hotel> createHoytel(@RequestBody Hotel hotel){
		Hotel hotelNew=hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelNew);
	}
	
	@GetMapping("/getHotelById/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
		Hotel hotel=hotelService.getHotelById(hotelId);
		return ResponseEntity.ok(hotel);
	}
	
	@GetMapping("/getAllHotel")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> hotelList=hotelService.getAllHotel();
		return ResponseEntity.ok(hotelList);
	}
}
