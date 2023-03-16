package com.jdc.balance.model.dto;

public record BalanceItemDto(
		long id,
		String reason,
		int unitPrice,
		int quentity) {

}
