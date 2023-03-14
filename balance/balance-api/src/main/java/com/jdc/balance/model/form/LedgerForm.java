package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.LedgerType;

public record LedgerForm(
		int id,
		LedgerType type,
		String name
		) {

}
