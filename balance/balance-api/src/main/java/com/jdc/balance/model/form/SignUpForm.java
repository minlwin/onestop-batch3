package com.jdc.balance.model.form;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter user name.")
		String name,
		@NotBlank(message = "Please enter login id.")
		String loginId,
		@NotBlank(message = "Please enter password.")
		String password) {

}
