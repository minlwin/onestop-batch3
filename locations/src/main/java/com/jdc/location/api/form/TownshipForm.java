package com.jdc.location.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TownshipForm(
		@NotBlank(message = "Please enter township name.")
		String name,
		@NotBlank(message = "Please enter burmese name.")
		String burmese,
		@NotNull(message = "Please select division.")
		Integer division
		) {

}
