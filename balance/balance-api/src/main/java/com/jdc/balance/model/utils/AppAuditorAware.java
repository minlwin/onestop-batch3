package com.jdc.balance.model.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.service.LoginUserService;

@Component
public class AppAuditorAware implements AuditorAware<Account>{

	@Autowired
	private LoginUserService service;
	
	@Override
	public Optional<Account> getCurrentAuditor() {
		return service.getLoginUser();
	}

}
