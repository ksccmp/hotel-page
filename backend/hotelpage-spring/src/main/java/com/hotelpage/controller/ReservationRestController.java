package com.hotelpage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelpage.dto.Reservation;
import com.hotelpage.service.ReservationServcie;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class ReservationRestController {
	
	@Autowired
	ReservationServcie Rservice;
	
	@GetMapping("/reservation/selectByUid")
	public ResponseEntity<Map<String, Object>> selectByUid(@RequestParam int uid) {
		try {
			log.trace("ReservationRestController: selectByUid");
			return response(Rservice.selectByUid(uid), HttpStatus.OK, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@PostMapping("/reservation/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Reservation reservation) {
		try {
			log.trace("ReservationRestController: insert");
			reservation.setStartdate(reservation.getStartdate().split(" ")[0]);
			reservation.setEnddate(reservation.getEnddate().split(" ")[0]);
			return response(Rservice.insert(reservation), HttpStatus.CREATED, true);
		} catch(RuntimeException e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}
	
	@DeleteMapping("/reservation/deleteById/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) {
		try {
			log.trace("ReservationRestController: delete");
			return response(Rservice.deleteById(id), HttpStatus.OK, true);
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
