package com.jdc.balance.model.dto;

import java.util.List;

public record MessageDto(
		Type type,
		List<String> messages
		) {
	
	public enum Type {
		Validation, Business, Platform
	}
}
