package com.jdc.balance.security;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.dto.Role;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class AppAdminUserInitializer {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	@EventListener(value = ContextRefreshedEvent.class)
	public void initAdminUser() {
		
		var adminUserCount = repo.countByRole(Role.Admin);
		
		if(adminUserCount == 0) {
			var admin = new Account();
			admin.setName("Admin User");
			admin.setLoginId("admin");
			admin.setPassword(encoder.encode("admin"));
			admin.setRole(Role.Admin);
			admin.setStatus(AccountStatus.Approved);
			admin.setRegistDate(LocalDate.now());
			
			repo.save(admin);
		}
	}
}
