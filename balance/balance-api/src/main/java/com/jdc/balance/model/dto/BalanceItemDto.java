package com.jdc.balance.model.dto;

import com.jdc.balance.model.entity.BalanceItem;

public record BalanceItemDto(
		long id,
		String reason,
		int unitPrice,
		int quentity) {

	public static BalanceItemDto from(BalanceItem entity) {
		return new BalanceItemDto(entity.getId(), entity.getReason(), entity.getUnitPrice(), entity.getQuentity());
	}
}
