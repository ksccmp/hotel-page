package com.hotelpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelpage.dto.Dates;
import com.hotelpage.dto.Logger;
import com.hotelpage.dto.Room;
import com.hotelpage.repository.LoggerRepository;
import com.hotelpage.repository.RoomRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	RoomRepository Rrepo;
	
	@Autowired
	LoggerRepository Lrepo;
	
	@Override
	public List<Room> selectAll() {
		log.trace("RoomServiceImpl: selectAll");
		Lrepo.insert(new Logger("Room", "selectAll", null));
		return Rrepo.selectAll();
	}
	
	@Override
	public Room selectById(int id) {
		log.trace("RoomServiceImpl: selectById");
		Lrepo.insert(new Logger("Room", "selectById", null));
		return Rrepo.selectById(id);
	}
	
	@Override
	public List<Room> selectByDates(Dates dates) {
		log.trace("RoomServiceImpl: selectByDates");
		Lrepo.insert(new Logger("Room", "selectByDates", null));
		return Rrepo.selectByDates(dates);
	}
	
	@Override
	public Room selectByDatesAndId(Dates dates) {
		log.trace("RoomServiceImpl: selectByDatesAndId");
		Lrepo.insert(new Logger("Room", "selectByDatesAndId", null));
		return Rrepo.selectByDatesAndId(dates);
	}
}
