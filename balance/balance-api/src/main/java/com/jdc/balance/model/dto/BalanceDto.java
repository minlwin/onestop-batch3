package com.jdc.balance.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.balance.model.entity.Balance;
import com.jdc.balance.model.form.LedgerForm;

public record BalanceDto(
		long id,
		LedgerForm ledger,
		@JsonFormat(pattern = "yyyy-MM-dd")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		String remark,
		List<BalanceItemDto> items
		) {

	public static BalanceDto from(Balance entity) {
		return new BalanceDto(
				entity.getId(), 
				LedgerForm.from(entity.getLedger()), 
				entity.getUseDate(), 
				entity.getRemark(), 
				entity.getItems().stream()
					.map(BalanceItemDto::from)
					.toList());
	}

}
