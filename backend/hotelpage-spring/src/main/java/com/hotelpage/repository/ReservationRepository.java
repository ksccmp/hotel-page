package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.Reservation;

public interface ReservationRepository {
	public List<Reservation> selectByUid(int uid);
	
	public int insert(Reservation reservation);
	
	public int deleteById(int id);
}
