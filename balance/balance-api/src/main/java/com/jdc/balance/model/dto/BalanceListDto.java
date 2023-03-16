package com.jdc.balance.model.dto;

import java.time.LocalDate;

import com.jdc.balance.model.form.LedgerForm;

public record BalanceListDto(
		long id,
		LocalDate useDate,
		LedgerForm ledger,
		int total,
		String remark
		) {

}
