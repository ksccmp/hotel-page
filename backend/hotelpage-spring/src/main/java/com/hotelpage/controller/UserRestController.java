package com.hotelpage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelpage.dto.User;
import com.hotelpage.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class UserRestController {
	
	@Autowired
	UserService Uservice;
	
	@GetMapping("/userSelectAll")
	public ResponseEntity<Map<String, Object>> selectAll() {
		try {
			log.trace("UserRestController: selectAll");
			return response(Uservice.selectAll(), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/userSelectById")
	public ResponseEntity<Map<String, Object>> selectById(User user) {
		try {
			log.trace("UserRestController: selectById");
			User targetUser = Uservice.selectById(user.getUserid());
			if(targetUser.getPassword().equals(user.getPassword())) {
				return response(targetUser, HttpStatus.OK, true);
			} else {
				return response(0, HttpStatus.OK, false);
			}
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@PostMapping("/userInsert")
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
