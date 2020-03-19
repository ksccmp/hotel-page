package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.Request;

public interface RequestRepository {
	public List<Request> selectAll();
	
	public int insert(Request request);
}
