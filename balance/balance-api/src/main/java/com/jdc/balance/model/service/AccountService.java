package com.jdc.balance.model.service;

import static com.jdc.balance.model.utils.Exceptions.entityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.form.AccountStatusForm;
import com.jdc.balance.model.repo.AccountRepo;


@Service
@Transactional(readOnly = true)
public class AccountService {
	
	@Autowired
	private AccountRepo repo;

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
}
