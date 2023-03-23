package com.jdc.balance.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.balance.model.form.LedgerForm;

public record BalanceReportItemDto(
		long id,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		LedgerForm ledger,
		long total,
		long balance
		) {

}
