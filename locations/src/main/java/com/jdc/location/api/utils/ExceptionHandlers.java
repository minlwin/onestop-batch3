package com.jdc.location.api.utils;

import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.location.api.utils.Message.Type;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(ValidationException.class)
	Message handle(ValidationException e) {
		return new Message(Type.Error, e.getMessages());
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	Message handle(DataNotFoundException e) {
		return new Message(Type.Error, List.of(e.getMessage()));
	}
}
