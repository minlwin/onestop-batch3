package com.jdc.balance.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.form.AccountStatusForm;

@RestController
@RequestMapping("account")
public class AccountApi {

	@GetMapping
	List<AccountDto> search(
			@RequestParam Optional<AccountStatus> status, 
			@RequestParam Optional<String> name) {
		return List.of();
	}
	
	@GetMapping("{id}")
	AccountDto findById(@PathVariable int id) {
		return null;
	}
	
	@PutMapping("status")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	AccountDto updateStatus(@Validated @RequestBody AccountStatusForm form, BindingResult result) {
		return null;
	}
}
