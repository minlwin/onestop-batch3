package com.jdc.balance.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record AccountDto(
	int id,
	String name,
	String loginId,
	AccountStatus status,
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate registDate,
	String email,
	String phone
		) {

}
