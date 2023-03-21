package com.jdc.balance.model.service;

import static com.jdc.balance.model.utils.Exceptions.entityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.dto.Role;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.form.AccountStatusForm;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.repo.AccountRepo;


@Service
@Transactional(readOnly = true)
public class AccountService {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public AccountDto updateStatus(AccountStatusForm form) {
		
		var entity = repo.findById(form.id())
				.orElseThrow(() -> entityNotFoundException("account", form.id()));
		
		entity.setStatus(form.status());
		
		return AccountDto.from(entity);
	}

	public AccountDto findById(int id) {
		return repo.findById(id).map(AccountDto::from)
				.orElseThrow(() -> entityNotFoundException("account", id));
	}

	public List<AccountDto> search(Optional<AccountStatus> status, Optional<String> name) {
		return repo.findAll(withStatus(status).and(withName(name)))
				.stream().map(AccountDto::from).toList();
	}
	
	private Specification<Account> withStatus(Optional<AccountStatus> data) {
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("status"), data.get());
	}

	private Specification<Account> withName(Optional<String> data) {
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.like(cb.lower(root.get("name")), data.get().toLowerCase().concat("%"));
	}

	@Transactional
	public void createMember(SignUpForm form) {

		var account = new Account();
		account.setName(form.name());
		account.setLoginId(form.loginId());
		account.setPassword(passwordEncoder.encode(form.password()));
		account.setRole(Role.Member);
		account.setStatus(AccountStatus.Apply);
		account.setRegistDate(LocalDate.now());
		
		repo.save(account);
	}
}
