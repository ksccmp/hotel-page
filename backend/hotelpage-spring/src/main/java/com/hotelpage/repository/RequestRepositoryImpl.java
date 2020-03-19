package com.hotelpage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotelpage.dto.Request;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RequestRepositoryImpl implements RequestRepository {
	private String ns = "hotelpage.requestmapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Request> selectAll() {
		log.trace("RequestRepositoryImpl: selectAll");
		return session.selectList(ns + "selectAll");
	}
	
	@Override
	public int insert(Request request) {
		log.trace("RequestRepositoryImpl: insert");
		return session.insert(ns + "insert", request);
	}
}
