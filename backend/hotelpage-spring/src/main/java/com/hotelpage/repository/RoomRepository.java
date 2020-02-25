package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.Room;

public interface RoomRepository {
	public List<Room> selectAll();
	
	public Room selectById(int id);
}
