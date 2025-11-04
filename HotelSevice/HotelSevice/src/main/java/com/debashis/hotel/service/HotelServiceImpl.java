package com.debashis.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debashis.hotel.entities.Hotel;
import com.debashis.hotel.exception.ResourceNotFoundException;
import com.debashis.hotel.repositiries.HotelRepository;

@Service
public class HotelServiceImpl  implements HotelService{

	@Autowired
	private HotelRepository hotelRespository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setHotelId(UUID.randomUUID().toString());
		return hotelRespository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRespository.findAll();
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		return hotelRespository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not exist on server"));
	}
	
}
