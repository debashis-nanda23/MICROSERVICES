package com.debashis.hotel.service;

import java.util.List;

import com.debashis.hotel.entities.Hotel;

public interface HotelService {
	
	public Hotel createHotel(Hotel hotel);
	public List<Hotel> getAllHotel();
	public Hotel getHotelById(String hotelId);

}
