package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.entity.Ledger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LedgerForm(
		int id,
		@NotNull(message = "Please select ledger type.")
		LedgerType type,
		@NotBlank(message = "Plase enter ledger name.")
		String name
		) {

	public Ledger newEntity() {
		var entity = new Ledger();
		entity.setType(type);
		entity.setName(name);
		return entity;
	}

	public static LedgerForm from(Ledger entity) {
		return new LedgerForm(entity.getId(), entity.getType(), entity.getName());
	}

}
