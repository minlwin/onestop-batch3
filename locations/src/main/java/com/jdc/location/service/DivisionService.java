package com.jdc.location.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.location.api.form.DivisionForm;
import com.jdc.location.api.utils.DataNotFoundException;
import com.jdc.location.entity.Division;
import com.jdc.location.repo.DivisionRepo;
import com.jdc.location.repo.DivisionTypeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class DivisionService {
	
	@Autowired
	private DivisionRepo repo;
	@Autowired
	private DivisionTypeRepo typeRepo;

	public Division findById(int id) {
		return repo.findById(id).orElseThrow(() -> new DataNotFoundException("There is no data with id %d.".formatted(id)));
	}

	@Transactional
	public Division create(DivisionForm data) {
		return repo.save(data.entity(id -> typeRepo.findById(id).orElseThrow(EntityNotFoundException::new)));
	}

	@Transactional
	public Division update(int id, DivisionForm data) {
		var entity = findById(id);
		entity.setName(data.name());
		entity.setBurmese(data.burmese());
		entity.setCapital(data.capital());
		entity.setType(typeRepo.findById(data.type()).orElseThrow(EntityNotFoundException::new));
		return repo.save(entity);
	}

	public List<Division> search(Optional<Integer> type, Optional<String> keyword) {
		return repo.findAll(whichType(type).and(whichKeyword(keyword.filter(StringUtils::hasLength))));
	}
	
	private Specification<Division> whichType(Optional<Integer> data) {
		// d.type.id = ?
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("type").get("id"), data.get());
	}
	
	private Specification<Division> whichKeyword(Optional<String> data) {
		// lower(d.name) like ? or d.burmese like ?
		return data.isEmpty() ? Specification.where(null) :
			(root, query, cb) -> cb.or(
					cb.like(cb.lower(root.get("name")), data.get().toLowerCase().concat("%")),
					cb.like(root.get("burmese"), data.get().concat("%"))
			);
	}
	

}
