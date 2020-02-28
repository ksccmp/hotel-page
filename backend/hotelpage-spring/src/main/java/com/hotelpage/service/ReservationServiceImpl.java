package com.hotelpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotelpage.dto.Logger;
import com.hotelpage.dto.Reservation;
import com.hotelpage.repository.LoggerRepository;
import com.hotelpage.repository.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationServcie {
	
	@Autowired
	ReservationRepository Rrepo;
	
	@Autowired
	LoggerRepository Lrepo;
	
	@Override
	public List<Reservation> selectByUid(int uid) {
		log.trace("ReservationServiceImpl: selectByUid");
		Lrepo.insert(new Logger("Reservation", "selectByUid", null));
		return Rrepo.selectByUid(uid);
	}
	
	@Override
	@Transactional
	public int insert(Reservation reservation) {
		log.trace("ReservationServiceImpl: insert");
		Lrepo.insert(new Logger("Reservation", "insert", null));
		return Rrepo.insert(reservation);
	}
	
	@Override
	@Transactional
	public int deleteById(int id) {
		log.trace("ReservationServiceImpl: delete");
		Lrepo.insert(new Logger("Reservation", "deleteById", null));
		return Rrepo.deleteById(id);
	}
}
