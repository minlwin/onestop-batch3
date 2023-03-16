package com.jdc.balance.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.BalanceListDto;
import com.jdc.balance.model.dto.BalanceReportDto;
import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.form.BalanceForm;

@RestController
@RequestMapping("balance")
public class BalanceApi {

	@GetMapping
	List<BalanceListDto> search(
			@RequestParam LedgerType type, 
			@RequestParam Optional<Integer> ledger,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dateFrom,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dateTo) {
		return List.of();
	}
	
	@GetMapping("report")
	BalanceReportDto searchReport(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dateFrom,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dateTo) {
		return null;
	}
	
	@GetMapping("{id}")
	BalanceDto findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	BalanceDto create(BalanceForm form) {
		return null;
	}
	
	@PutMapping
	BalanceDto update(BalanceForm form) {
		return null;
	}
}
