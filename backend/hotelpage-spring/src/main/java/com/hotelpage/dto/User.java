package com.hotelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userid;
	private String password;
	private String nickname;
	private String position;
	private String gender;
	private int age;
	private String birth;
	private String phone;
	private String email;
	private String registerdate;
	private String updatedate;
}
