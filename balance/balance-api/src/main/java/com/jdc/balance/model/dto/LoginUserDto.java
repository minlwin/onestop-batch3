package com.jdc.balance.model.dto;

public record LoginUserDto(
	int id,
	String loginId,
	String name,
	Role role,
	AccountStatus status
		) {

}
