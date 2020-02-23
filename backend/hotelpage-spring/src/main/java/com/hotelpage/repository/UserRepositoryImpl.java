package com.hotelpage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotelpage.dto.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
	private String ns = "hotelpage.usermapper.";
	
	@Autowired
	SqlSession session;
	
	public List<User> selectAll() {
		log.trace("UserRepositoryImpl: selectAll");
		return session.selectList(ns + "selectAll");
	}
	
	public int insert(User user) {
		log.trace("UserRepositoryImpl: insert");
		return session.insert(ns + "insert", user);
	}
}
