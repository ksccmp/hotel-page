package com.hotelpage.service;

import java.util.List;

import com.hotelpage.dto.Request;

public interface RequestService {
	public List<Request> selectAll();
	
	public int insert(Request request);
}
