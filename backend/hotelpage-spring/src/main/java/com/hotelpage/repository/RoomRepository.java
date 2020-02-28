package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.Dates;
import com.hotelpage.dto.Room;

public interface RoomRepository {
	public List<Room> selectAll();
	
	public Room selectById(int id);
	
	public List<Room> selectByDates(Dates dates);
	
	public Room selectByDatesAndId(Dates dates);
}
