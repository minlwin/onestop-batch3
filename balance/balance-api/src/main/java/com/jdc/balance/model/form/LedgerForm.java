package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.LedgerType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LedgerForm(
		int id,
		@NotNull(message = "Please select ledger type.")
		LedgerType type,
		@NotBlank(message = "Plase enter ledger name.")
		String name
		) {

}
