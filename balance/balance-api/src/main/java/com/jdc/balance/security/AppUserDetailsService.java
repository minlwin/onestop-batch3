package com.jdc.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByLoginId(username)
			.map(user -> User.builder()
					.username(username)
					.password(user.getPassword())
					.authorities(user.getRole().name())
					.accountLocked(user.getStatus() == AccountStatus.Denied)
					.build())
			.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
