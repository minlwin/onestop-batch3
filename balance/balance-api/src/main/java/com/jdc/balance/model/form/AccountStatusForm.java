package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.AccountStatus;

import jakarta.validation.constraints.NotNull;

public record AccountStatusForm(
		@NotNull(message = "You need to set account id.")
		Integer id,
		@NotNull(message = "Please enter account status.")
		AccountStatus status
		) {

}
