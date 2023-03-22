package com.jdc.balance.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.balance.model.entity.Account;

public record AccountDto(
	int id,
	String name,
	String loginId,
	AccountStatus status,
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate registDate,
	String email,
	String phone
		) {

	public static AccountDto from(Account entity) {
		return new AccountDto(entity.getId(), entity.getName(), entity.getLoginId(), 
				entity.getStatus(), entity.getRegistDate(), entity.getEmail(), entity.getPhone());
	}

}
