package com.hotelpage.service;

import java.util.List;

import com.hotelpage.dto.User;

public interface UserService {
	public List<User> selectAll();
	
	public User selectById(String userid);
	
	public int insert(User user);
}
