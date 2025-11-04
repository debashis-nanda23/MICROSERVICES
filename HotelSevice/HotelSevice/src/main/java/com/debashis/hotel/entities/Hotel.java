package com.debashis.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="micro_hotel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

	@Id
	@Column(name="hotelid_pk")
	private String hotelId;
	
	@Column(name="hotel_name")
	private String name;
	
	@Column(name="hotel_location")
	private String location;
	
	@Column(name="hotel_about")
	private String about;
	
}
