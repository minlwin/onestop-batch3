package com.jdc.balance.model.form;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public record BalanceForm(
		long id,
		int ledger,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		String remark,
		List<BalanceItemForm> items
		) {

}
