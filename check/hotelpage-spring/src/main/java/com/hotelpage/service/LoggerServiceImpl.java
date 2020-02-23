package com.hotelpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelpage.dto.Logger;
import com.hotelpage.repository.LoggerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoggerServiceImpl implements LoggerService {
	
	@Autowired
	LoggerRepository Lrepo;
	
	@Override
	public List<Logger> selectAll() {
		log.trace("LogServiceImpl: selectAll");
		return Lrepo.selectAll();
	}
}
