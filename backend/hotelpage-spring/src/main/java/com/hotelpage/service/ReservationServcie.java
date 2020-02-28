package com.hotelpage.service;

import java.util.List;

import com.hotelpage.dto.Reservation;

public interface ReservationServcie {
	public List<Reservation> selectByUid(int uid);
	
	public int insert(Reservation reservation);
	
	public int deleteById(int id);
}
