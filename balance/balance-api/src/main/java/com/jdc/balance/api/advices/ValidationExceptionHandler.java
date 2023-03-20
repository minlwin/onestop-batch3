package com.jdc.balance.api.advices;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.utils.BalanceAppException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(ValidationException.class)
	public MessageDto handle(ValidationException e) {
		return new MessageDto(Type.Validation, e.getMessages());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(BalanceAppException.class)
	public MessageDto handle(BalanceAppException e) {
		return new MessageDto(Type.Validation, e.getMessages());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_MODIFIED)
	@ExceptionHandler(EntityNotFoundException.class)
	public MessageDto handle(EntityNotFoundException e) {
		return new MessageDto(Type.Business, List.of(e.getMessage()));
	}
	
}
