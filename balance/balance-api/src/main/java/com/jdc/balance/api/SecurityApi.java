package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.LoginUserDto;
import com.jdc.balance.model.form.SignInForm;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.LoginUserService;

@RestController
@RequestMapping("security")
public class SecurityApi {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private LoginUserService service;
	
	@Autowired
	private AccountService accountService;

	@PostMapping("sign-in")
	LoginUserDto signIn(@Validated @RequestBody SignInForm form, BindingResult result) {
		return signIn(form);
	}
	
	@PostMapping("sign-up")
	LoginUserDto signUp(@Validated @RequestBody SignUpForm form, BindingResult result) {
		
		// Create Account
		accountService.createMember(form);
		
		// Sign In
		return signIn(form.signInForm());
	}
	
	private LoginUserDto signIn(SignInForm form) {
		var authToken = new UsernamePasswordAuthenticationToken(form.loginId(), form.password());
		var authResult = authenticationManager.authenticate(authToken);
		
		SecurityContextHolder.getContext().setAuthentication(authResult);
		
		return service.getLoginUser()
				.map(LoginUserDto::from).orElseThrow();
	}
}
