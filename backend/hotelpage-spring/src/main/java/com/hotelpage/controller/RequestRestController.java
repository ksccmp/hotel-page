package com.hotelpage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelpage.dto.Request;
import com.hotelpage.service.RequestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RequestRestController {
	
	@Autowired
	RequestService Rservice;
	
	@GetMapping("/request/selectAll")
	public ResponseEntity<Map<String, Object>> selectAll() {
		try {
			log.trace("RequestRestController: selectAll");
			return response(Rservice.selectAll(), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@PostMapping("/request/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Request request) {
		try {
			log.trace("RequestRestController: insert");
			return response(Rservice.insert(request), HttpStatus.CREATED, true);
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
