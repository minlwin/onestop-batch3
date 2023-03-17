package com.jdc.balance.model.service;

import static com.jdc.balance.model.utils.Exceptions.entityNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.dto.UploadResultDto;
import com.jdc.balance.model.entity.Ledger;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.repo.LedgerRepo;


@Service
@Transactional
public class LedgerService {
	
	@Autowired
	private LedgerRepo repo;
	
	@Autowired
	private LoginUserService loginUserService;

	public LedgerForm create(LedgerForm form) {
		var entity = repo.save(form.newEntity());
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
			var count = 0;
			
			while(null != (line = br.readLine())) {
				var array = line.split("\t");
				var entity = new Ledger();
				entity.setType(LedgerType.valueOf(array[0]));
				entity.setName(array[1]);
				
				repo.save(entity);
				count ++;
			}
			
			return UploadResultDto.success(count);
		} catch (Exception e) {
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
