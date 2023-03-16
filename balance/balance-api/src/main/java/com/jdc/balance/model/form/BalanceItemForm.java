package com.jdc.balance.model.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BalanceItemForm(
		long id,
		@NotBlank(message = "Please enter reason.")
		String reason,
		@Min(value = 1, message = "Please enter unit price.")
		int unitPrice,
		@Min(value = 1, message = "Please enter quentity.")
		int quentity,
		boolean deleted) {

}
