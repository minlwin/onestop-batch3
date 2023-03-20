package com.jdc.balance.model.service;

import static com.jdc.balance.model.utils.Exceptions.entityNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.dto.UploadResultDto;
import com.jdc.balance.model.entity.Ledger;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.repo.LedgerRepo;
import com.jdc.balance.model.utils.BalanceAppException;


@Service
@Transactional(rollbackFor = {
		BalanceAppException.class
})
public class LedgerService {
	
	@Autowired
	private LedgerRepo repo;
	
	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private LocalValidatorFactoryBean validatorFactoryBean;

	public LedgerForm create(LedgerForm form) {
		
		var loginUser = loginUserService.getLoginUser().orElseThrow();
		
		var entity = repo.findOneByOwnerAndName(loginUser, form.name());
		if(null != entity) {
			entity.setType(form.type());
			return LedgerForm.from(entity);
		}
		
		entity = form.newEntity();
		entity.setOwner(loginUser);
		
		entity = repo.save(entity);
		return LedgerForm.from(entity);
	}

	public LedgerForm update(LedgerForm form) {
		var entity = repo.findById(form.id())
				.orElseThrow(() -> entityNotFoundException("ledger", form.id()));
		entity.setType(form.type());
		entity.setName(form.name());
		return LedgerForm.from(entity);
	}

	public UploadResultDto upload(MultipartFile file) {
		
		try(var br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = null;
			class Counter {
				int count;
				
				void countUp() {
					count ++;
				}
			}
			var counter = new Counter();
			var messages = new ArrayList<String>();
			
			while(null != (line = br.readLine())) {
				
				counter.countUp();
				var array = line.split("\t");
				
				if(array.length != 2) {
					messages.add("Line %d : Data Format Error.".formatted(counter.count));
					continue;
				}
				
				if(!LedgerType.validateName(array[0])) {
					messages.add("Line %d : '%s' is not a valid ledger type.".formatted(counter.count, array[0]));
					continue;
				}
				
				var form = new LedgerForm(0, LedgerType.valueOf(array[0]), array[1]);
				var result = new BeanPropertyBindingResult(form, "form");
				validatorFactoryBean.validate(form, result);
				
				if(result.hasErrors()) {
					result.getFieldErrors().stream()
						.map(a -> a.getDefaultMessage())
						.map(a -> "Line %d : %s".formatted(counter.count, a))
						.forEach(messages::add);
					continue;
				}
				
				create(form);
			}
			
			if(!messages.isEmpty()) {
				throw new BalanceAppException(messages);
			}
			
			return UploadResultDto.success(counter.count);
		} catch (IOException e) {
			return UploadResultDto.fails("File Format Error");
		}
	}

	@Transactional(readOnly = true)
	public List<LedgerForm> search(Optional<LedgerType> type) {
		return repo.findAll(withOwner().and(withType(type)))
				.stream().map(LedgerForm::from).toList();
	}
	
	private Specification<Ledger> withType(Optional<LedgerType> type) {
		return type.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("type"), type.get());
	}
	
	private Specification<Ledger> withOwner() {
		return (root, query, cb) -> cb.equal(root.get("owner"), loginUserService.getLoginUser()
				.orElseThrow());
	}

}
