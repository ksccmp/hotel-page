package com.hotelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logger {
	private String tablename;
	private String action;
	private String registerdate;
}
