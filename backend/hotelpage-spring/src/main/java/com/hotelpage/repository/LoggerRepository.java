package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.Logger;

public interface LoggerRepository {
	public List<Logger> selectAll();
	
	public int insert(Logger logger);
}
