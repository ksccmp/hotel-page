package com.hotelpage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotelpage.dto.Logger;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class LoggerRepositoryImpl implements LoggerRepository {
	
	private String ns = "hotelpage.loggermapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Logger> selectAll() {
		log.trace("LoggerRepositoryImpl: selectAll");
		return session.selectList(ns + "selectAll");
	}
	
	@Override
	public int insert(Logger logger) {
		log.trace("LoggerRepositoryImpl: insert");
		return session.insert(ns + "insert", logger);
	}
}
