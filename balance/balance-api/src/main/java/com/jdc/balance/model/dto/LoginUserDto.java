package com.jdc.balance.model.dto;

import com.jdc.balance.model.entity.Account;

public record LoginUserDto(
	int id,
	String loginId,
	String name,
	Role role,
	AccountStatus status
		) {

	public static LoginUserDto from(Account entity) {
		return new LoginUserDto(entity.getId(), entity.getLoginId(), entity.getName(), 
				entity.getRole(), entity.getStatus());
	}
}
