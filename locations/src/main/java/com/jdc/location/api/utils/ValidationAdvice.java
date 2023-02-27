package com.jdc.location.api.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationAdvice {

	@Before(value = "@within(org.springframework.web.bind.annotation.RestController) && args(..,result)", argNames = "result")
	public void check(BindingResult result) {
		if(result.hasErrors()) {
			throw new ValidationException(result.getFieldErrors());
		}
	}
}
