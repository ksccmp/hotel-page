package com.hotelpage.repository;

import java.util.List;

import com.hotelpage.dto.User;

public interface UserRepository {
	public List<User> selectAll();
	
	public int insert(User user);
}
