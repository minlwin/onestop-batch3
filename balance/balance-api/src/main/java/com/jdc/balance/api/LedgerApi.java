package com.jdc.balance.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.model.dto.LedgerDto;
import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.dto.UploadResultDto;
import com.jdc.balance.model.form.LedgerForm;

@RestController
@RequestMapping("ledger")
public class LedgerApi {

	@GetMapping
	public List<LedgerDto> search(@RequestParam Optional<LedgerType> type) {
		return List.of();
	}
	
	@PostMapping
	public LedgerDto create(@RequestBody LedgerForm form) {
		return null;
	}

	@PutMapping
	public LedgerDto update(@RequestBody LedgerForm form) {
		return null;
	}
	
	@PostMapping("upload")
	public UploadResultDto upload(@RequestParam MultipartFile file) {
		return null;
	}
}
