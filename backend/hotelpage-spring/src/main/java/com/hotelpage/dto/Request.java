package com.hotelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
	private int id;
	private String type;
	private String title;
	private String contents;
	private String nickname;
	private String phone;
	private String email;
	private String solve;
}
