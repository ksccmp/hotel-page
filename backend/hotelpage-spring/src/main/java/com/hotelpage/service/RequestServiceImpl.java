package com.hotelpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotelpage.dto.Logger;
import com.hotelpage.dto.Request;
import com.hotelpage.repository.LoggerRepository;
import com.hotelpage.repository.RequestRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	RequestRepository Rrepo;
	
	@Autowired
	LoggerRepository Lrepo;
	
	@Override
	public List<Request> selectAll() {
		log.trace("RequestServiceImpl: selectAll");
		Lrepo.insert(new Logger("Request", "selectAll", null));
		return Rrepo.selectAll();
	}
	
	@Override
	@Transactional
	public int insert(Request request) {
		log.trace("RequestServiceImpl: insert");
		Lrepo.insert(new Logger("Request", "insert", null));
		return Rrepo.insert(request);
	}
}
