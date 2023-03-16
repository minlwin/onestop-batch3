package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.AccountStatus;

import jakarta.validation.constraints.NotNull;

public record AccountStatusForm(
		int id,
		@NotNull(message = "Please enter account status.")
		AccountStatus status
		) {

}
