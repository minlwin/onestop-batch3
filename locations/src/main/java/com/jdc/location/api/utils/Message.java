package com.jdc.location.api.utils;

import java.util.List;

public record Message(
		Type type,
		List<String> messages) {
	
	public enum Type {
		Information, Alert, Error
	}
}
