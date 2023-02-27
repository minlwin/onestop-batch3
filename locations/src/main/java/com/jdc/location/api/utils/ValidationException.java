package com.jdc.location.api.utils;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<FieldError> errors;

	public ValidationException(List<FieldError> errors) {
		this.errors = errors;
	}
	
	public List<String> getMessages() {
		return errors.stream().map(e -> e.getDefaultMessage()).toList();
	}
}
