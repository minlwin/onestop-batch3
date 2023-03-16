package com.jdc.balance.model.form;

public record BalanceItemForm(
		long id,
		String reason,
		int unitPrice,
		int quentity,
		boolean deleted) {

}
