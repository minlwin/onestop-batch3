package com.jdc.balance.model.form;

import jakarta.validation.constraints.NotBlank;

public record SignInForm(
		@NotBlank(message = "Please enter login id.")
		String loginId, 
		@NotBlank(message = "Please enter password.")
		String password) {

}
