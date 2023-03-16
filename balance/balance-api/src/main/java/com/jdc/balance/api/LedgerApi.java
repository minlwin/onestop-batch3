package com.jdc.balance.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.dto.UploadResultDto;
import com.jdc.balance.model.form.LedgerForm;

@RestController
@RequestMapping("ledger")
public class LedgerApi {

	@GetMapping
	public List<LedgerForm> search(@RequestParam Optional<LedgerType> type) {
		return List.of();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public LedgerForm create(@Validated @RequestBody LedgerForm form, BindingResult result) {
		return null;
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public LedgerForm update(@Validated @RequestBody LedgerForm form, BindingResult result) {
		return null;
	}
	
	@PostMapping("upload")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UploadResultDto upload(@RequestParam MultipartFile file) {
		return null;
	}
}
