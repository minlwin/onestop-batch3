package com.jdc.balance.api.advices;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationAspects {
	
	@Pointcut("within(com.jdc.balanc.api.*Api) and @within(org.springframework.web.bind.annotation.RestController)")
	public void allApiClasses() {}
	
	@Before(value = "appAipClasses() and args(..,result)", argNames = "result")
	public void checkBindingResult(BindingResult result) {
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
	}
}
