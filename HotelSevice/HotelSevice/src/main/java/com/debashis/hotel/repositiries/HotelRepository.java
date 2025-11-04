package com.debashis.hotel.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debashis.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

	
}
