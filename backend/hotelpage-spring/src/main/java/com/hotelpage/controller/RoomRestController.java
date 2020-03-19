package com.hotelpage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelpage.dto.Dates;
import com.hotelpage.service.RoomService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class RoomRestController {
	
	@Autowired
	RoomService RService;
	
	@GetMapping("/room/selectAll")
	public ResponseEntity<Map<String, Object>> selectAll() {
		try {
			log.trace("RoomRestController: selectAll");
			return response(RService.selectAll(), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/room/selectById")
	public ResponseEntity<Map<String, Object>> selectById(@RequestParam int id) {
		try {
			log.trace("RoomRestController: selectById");
			return response(RService.selectById(id), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/room/selectByDates")
	public ResponseEntity<Map<String, Object>> selectByDates(Dates dates) {
		try {
			log.trace("RoomRestController: selectByDates");
			return response(RService.selectByDates(dates), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@GetMapping("/room/selectByDatesAndId")
	public ResponseEntity<Map<String, Object>> selectByDatesAndId(Dates dates) {
		try {
			log.trace("RoomRestController: selectByDatesAndId");
			return response(RService.selectByDatesAndId(dates), HttpStatus.OK, true);
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
