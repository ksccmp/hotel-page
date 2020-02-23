package com.hotelpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotelpage.dto.Logger;
import com.hotelpage.dto.User;
import com.hotelpage.repository.LoggerRepository;
import com.hotelpage.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository Urepo;
	
	@Autowired
	LoggerRepository Lrepo;
	
	public List<User> selectAll() {
		log.trace("UserServiceImpl: selectAll");
		Lrepo.insert(new Logger("User", "selectAll", null));
		return Urepo.selectAll();
	}
	
	@Transactional
	public int insert(User user) {
		log.trace("UserServiceImpl: insert");
		Lrepo.insert(new Logger("User", "insert", null));
		return Urepo.insert(user);
	}
}
