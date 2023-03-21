package com.jdc.balance.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class LoginUserService {

	@Autowired
	private AccountRepo repo;
	
	public Optional<Account> getLoginUser() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.isAuthenticated()) {
			var loginId = authentication.getName();
			return repo.findOneByLoginId(loginId);
		}
		
		return Optional.empty();
	}

}
