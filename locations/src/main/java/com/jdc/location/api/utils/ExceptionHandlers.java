package com.jdc.location.api.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.location.api.utils.Message.Type;

@RestControllerAdvice
public class ExceptionHandlers {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	Message handle(ValidationException e) {
		return new Message(Type.Error, e.getMessages());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataNotFoundException.class)
	Message handle(DataNotFoundException e) {
		return new Message(Type.Error, List.of(e.getMessage()));
	}
}
