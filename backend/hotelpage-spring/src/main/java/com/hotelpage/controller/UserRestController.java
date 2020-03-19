package com.hotelpage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelpage.dto.User;
import com.hotelpage.jwt.JwtService;
import com.hotelpage.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class UserRestController {
	
	@Autowired
	UserService Uservice;
	
	@Autowired
	JwtService Jservice;
	
	@GetMapping("/user/selectAll")
	public ResponseEntity<Map<String, Object>> selectAll() {
		try {
			log.trace("UserRestController: selectAll");
			return response(Uservice.selectAll(), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/user/selectById")
	public ResponseEntity<Map<String, Object>> selectById(User user, HttpServletResponse res) {
		try {
			log.trace("UserRestController: selectById");
			User targetUser = Uservice.selectById(user.getUserid());
			if(targetUser.getPassword().equals(user.getPassword())) {
				String token = Jservice.createToken(targetUser); // user를 저장하는 토큰 만들기
				res.setHeader("jwt-auth-token", token); // response의 header에 jwt-auth-token이라는 이름으로 만들어진 토큰을 담아 client에 전달
				
				return response(targetUser, HttpStatus.OK, true);
			} else {
				return response(0, HttpStatus.OK, false);
			}
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/user/selectByToken")
	public ResponseEntity<Map<String, Object>> selectByToken(String token) {
		try {
			log.trace("UserRestController: selectByToken");
			Map<String, Object> tokenData = Jservice.getUser(token);
			return response(tokenData.get("user"), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@PostMapping("/user/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody User user) {
		try {
			log.trace("UserRestController: insert");
			return response(Uservice.insert(user), HttpStatus.CREATED, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpstatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("status", status);
		return new ResponseEntity<Map<String,Object>>(resultMap, httpstatus);
	}
}
