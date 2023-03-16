package com.jdc.balance.api.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(ValidationException.class)
	public MessageDto handle(ValidationException e) {
		return new MessageDto(Type.Validation, e.getMessages());
	}
}
