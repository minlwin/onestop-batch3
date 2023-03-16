package com.jdc.balance.model.form;

import com.jdc.balance.model.dto.AccountStatus;

public record AccountStatusForm(
		int id,
		AccountStatus status
		) {

}
