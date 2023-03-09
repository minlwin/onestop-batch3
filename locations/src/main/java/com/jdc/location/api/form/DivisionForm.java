package com.jdc.location.api.form;

import java.util.function.Function;

import com.jdc.location.entity.Division;
import com.jdc.location.entity.DivisionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DivisionForm(
		@NotBlank(message = "Please enter name.")
		String name,
		@NotBlank(message = "Please enter burmese name.")
		String burmese,
		@NotNull(message = "Please select category.")
		Integer type,
		@NotBlank(message = "Please enter capital name.")
		String capital
		) {

	public Division entity(Function<Integer, DivisionType> typeMapper) {
		var entity = new Division();
		entity.setName(name);
		entity.setBurmese(burmese);
		entity.setCapital(capital);
		entity.setType(typeMapper.apply(type));
		return entity;
	}

}
