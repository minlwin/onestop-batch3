package com.jdc.balance.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.balance.model.entity.Balance;
import com.jdc.balance.model.form.LedgerForm;

public record BalanceListDto(
		long id,
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		LedgerForm ledger,
		String remark,
		int total) {

	public static BalanceListDto from(Balance entity) {
		return new BalanceListDto(entity.getId(), 
				entity.getUseDate(), 
				LedgerForm.from(entity.getLedger()), 
				entity.getRemark(),
				entity.getItems().stream()
					.mapToInt(a -> a.getQuentity() * a.getUnitPrice()).sum());
	}
}
