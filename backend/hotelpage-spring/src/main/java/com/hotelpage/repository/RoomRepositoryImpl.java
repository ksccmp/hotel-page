package com.hotelpage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotelpage.dto.Room;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RoomRepositoryImpl implements RoomRepository {
	private String ns = "hotelpage.roommapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Room> selectAll() {
		log.trace("RoomRepositoryImpl: selectAll");
		return session.selectList(ns + "selectAll");
	}
	
	@Override
	public Room selectById(int id) {
		log.trace("RoomRepositoryImpl: selectById");
		return session.selectOne(ns + "selectById", id);
	}
}
