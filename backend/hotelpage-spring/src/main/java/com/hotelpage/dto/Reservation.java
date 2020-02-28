package com.hotelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	private int id;
	private String uid;
	private int rid;
	private String roomname;
	private int roomnum;
	private String name;
	private int price;
	private String phone;
	private String form;
	private String way;
	private int person;
	private String startdate;
	private String enddate;
}
