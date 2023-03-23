package com.jdc.balance.model.dto;

import java.util.List;

public record BalanceReportDto(
		long lastBlance,
		long netBalance,
		List<BalanceReportItemDto> list
		) {

}
