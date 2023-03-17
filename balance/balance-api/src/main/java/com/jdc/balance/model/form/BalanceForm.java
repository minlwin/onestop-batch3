package com.jdc.balance.model.form;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BalanceForm(
		Long id,
		@NotNull(message = "Please select ledger.")
		Integer ledger,
		@NotNull(message = "Please enter use date.")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate useDate,
		String remark,
		@NotEmpty(message = "Please enter balance details items.")
		List<BalanceItemForm> items
		) {

}
