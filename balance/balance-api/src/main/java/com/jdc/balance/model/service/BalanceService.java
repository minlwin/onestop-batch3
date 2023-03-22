package com.jdc.balance.model.service;

import static com.jdc.balance.model.Exceptions.entityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.BalanceListDto;
import com.jdc.balance.model.dto.BalanceReportDto;
import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.entity.Balance;
import com.jdc.balance.model.entity.BalanceItem;
import com.jdc.balance.model.form.BalanceForm;
import com.jdc.balance.model.form.BalanceItemForm;
import com.jdc.balance.model.repo.BalanceItemRepo;
import com.jdc.balance.model.repo.BalanceRepo;
import com.jdc.balance.model.repo.LedgerRepo;


@Service
@Transactional(readOnly = true)
public class BalanceService {
	
	@Autowired
	private BalanceRepo balanceRepo;
	@Autowired
	private BalanceItemRepo itemRepo;
	@Autowired
	private LedgerRepo ledgerRepo;
	
	@Autowired
	private LoginUserService userService;

	@Transactional
	public BalanceDto create(BalanceForm form) {
		
		var loginUser = userService.getLoginUser().orElseThrow();
		
		var ledger = ledgerRepo.findById(form.ledger())
				.orElseThrow(() -> entityNotFoundException("ledger", form.ledger()));
		
		var balance = new Balance();
		balance.setLedger(ledger);
		balance.setRemark(form.remark());
		balance.setUseDate(form.useDate());
		balance.setOwner(loginUser);
		
		balance = balanceRepo.saveAndFlush(balance);
		
		for(var itemForm : form.items()) {
			createItem(itemForm, balance);
		}
		
		return findById(balance.getId());
	}

	@Transactional
	public BalanceDto update(BalanceForm form) {
		
		var balance = balanceRepo.findById(form.id())
				.orElseThrow(() -> entityNotFoundException("balance", form.id()));
		
		var ledger = ledgerRepo.findById(form.ledger())
				.orElseThrow(() -> entityNotFoundException("ledger", form.ledger()));
		
		balance.setLedger(ledger);
		balance.setRemark(form.remark());
		balance.setUseDate(form.useDate());
		
		for(var itemForm : form.items()) {
			if(itemForm.id() == 0L) {
				// Create
				createItem(itemForm, balance);
			} else {
				if(itemForm.deleted()) {
					// Delete
					itemRepo.deleteById(itemForm.id());
				} else {
					// Update
					var item = itemRepo.findById(itemForm.id())
							.orElseThrow(() -> entityNotFoundException("balance item", itemForm.id()));
					item.setReason(itemForm.reason());
					item.setQuentity(itemForm.quentity());
					item.setUnitPrice(itemForm.unitPrice());
				}
			}
		}
		
		return findById(balance.getId());
	}

	public BalanceDto findById(long id) {
		var balance = balanceRepo.findById(id)
				.orElseThrow(() -> entityNotFoundException("balance", id));
		
		return BalanceDto.from(balance);
	}

	public BalanceReportDto searchReport(Optional<LocalDate> dateFrom, Optional<LocalDate> dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BalanceListDto> search(
			LedgerType type, 
			Optional<Integer> ledger, 
			Optional<LocalDate> dateFrom,
			Optional<LocalDate> dateTo) {
		return balanceRepo.findAll(
				withOwner()
				.and(withLedgerOrType(type, ledger))
				.and(withDateFrom(dateFrom))
				.and(withDateTo(dateTo)))
				.stream().map(BalanceListDto::from).toList();
	}
	
	private Specification<Balance> withOwner() {
		return (root, query, cb) -> cb.equal(root.get("owner"), userService.getLoginUser()
				.orElseThrow());
	}
	
	private Specification<Balance> withLedgerOrType(LedgerType type, Optional<Integer> ledger) {
		return ledger.isEmpty() ? (root, query, cb) -> cb.equal(root.get("ledger").get("type"), type) : 
			(root, query, cb) -> cb.equal(root.get("ledger").get("id"), ledger.get());
	}
	
	private Specification<Balance> withDateFrom(Optional<LocalDate> data) {
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.greaterThanOrEqualTo(root.get("useDate"), data.get());
	}

	private Specification<Balance> withDateTo(Optional<LocalDate> data) {
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.lessThanOrEqualTo(root.get("useDate"), data.get());
	}

	private void createItem(BalanceItemForm itemForm, Balance balance) {
		var item = new BalanceItem();
		item.setBalance(balance);
		item.setReason(itemForm.reason());
		item.setQuentity(itemForm.quentity());
		item.setUnitPrice(itemForm.unitPrice());
		itemRepo.saveAndFlush(item);
	}


}
