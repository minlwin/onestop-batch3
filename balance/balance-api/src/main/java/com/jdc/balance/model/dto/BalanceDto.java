package com.jdc.balance.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.balance.model.form.LedgerForm;

public record BalanceDto(
		long id,
		LedgerForm ledger,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		String remark,
		List<BalanceItemDto> items
		) {

}
