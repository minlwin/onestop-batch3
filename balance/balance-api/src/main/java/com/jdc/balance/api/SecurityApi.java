package com.jdc.balance.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.LoginUserDto;
import com.jdc.balance.model.form.SignInForm;
import com.jdc.balance.model.form.SignUpForm;

@RestController
@RequestMapping("security")
public class SecurityApi {

	@PostMapping("sign-in")
	LoginUserDto signIn(SignInForm form) {
		return null;
	}
	
	@PostMapping("sign-up")
	LoginUserDto signUp(SignUpForm form) {
		return null;
	}
}
