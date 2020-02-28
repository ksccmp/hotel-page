package com.hotelpage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotelpage.dto.Reservation;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ReservationRepositoryImpl implements ReservationRepository {
	private String ns = "hotelpage.reservationmapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Reservation> selectByUid(int uid) {
		log.trace("ReservationRepositoryImpl: selectByUid");
		return session.selectList(ns + "selectByUid", uid);
	}
	
	@Override
	public int insert(Reservation reservation) {
		log.trace("ReservationRepositoryImpl: insert");
		return session.insert(ns + "insert", reservation);
	}
	
	@Override
	public int deleteById(int id) {
		log.trace("ReservationRepositoryImpl: deleteById");
		return session.delete(ns + "deleteById", id);
	}
}
