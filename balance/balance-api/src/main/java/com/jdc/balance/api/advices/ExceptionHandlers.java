package com.jdc.balance.api.advices;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.balance.model.dto.MessageDto;
import com.jdc.balance.model.dto.MessageDto.Type;
import com.jdc.balance.model.utils.BalanceAppException;
import com.jdc.balance.security.LoginIdInvalidException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public MessageDto handle(ValidationException e) {
		return new MessageDto(Type.Validation, e.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public MessageDto handle(BalanceAppException e) {
		return new MessageDto(Type.Validation, e.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public MessageDto handle(EntityNotFoundException e) {
		return new MessageDto(Type.Business, List.of(e.getMessage()));
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public MessageDto handle(BadCredentialsException e) {
		return new MessageDto(Type.Platform, List.of("Please check your password."));
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public MessageDto handle(LoginIdInvalidException e) {
		return new MessageDto(Type.Platform, List.of("Please check your login id."));
	}
}
