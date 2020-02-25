package com.hotelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
	private int id;
	private int num;
	private String name;
	private String contents;
	private String addcontents;
	private int size;
	private int standard;
	private int max;
	private int rentprice;
	private int rentaddprice;
	private int fullprice;
	private int fulladdprice;
}
