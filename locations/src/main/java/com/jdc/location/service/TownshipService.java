package com.jdc.location.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.api.form.TownshipForm;
import com.jdc.location.api.utils.DataNotFoundException;
import com.jdc.location.entity.Township;
import com.jdc.location.repo.DivisionRepo;
import com.jdc.location.repo.TownshipRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class TownshipService {
	
	@Autowired
	private TownshipRepo repo;
	@Autowired
	private DivisionRepo divRepo;

	public Township findById(int id) {
		return repo.findById(id).orElseThrow(() -> new DataNotFoundException("There is no data with id %d.".formatted(id)));
	}

	@Transactional
	public Township create(TownshipForm data) {
		return repo.save(entity(data));
	}

	@Transactional
	public Township update(int id, TownshipForm data) {
		var entity = entity(data);
		entity.setId(id);
		return repo.save(entity);
	}

	public List<Township> search(Optional<Integer> type, Optional<Integer> division, Optional<String> keyword) {
		return repo.findAll(whichType(type).and(whichDivision(division).and(whichKeyword(keyword))));
	}
	
	private Specification<Township> whichType(Optional<Integer> data) {
		// t.division.id = ?
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("division").get("type").get("id"), data.get());
	}

	private Specification<Township> whichDivision(Optional<Integer> data) {
		// t.division.id = ?
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("division").get("id"), data.get());
	}
	
	private Specification<Township> whichKeyword(Optional<String> data) {
		// lower(t.name) like ? or t.burmese like ? 
		return data.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.or(
				cb.like(cb.lower(root.get("name")), data.get().toLowerCase().concat("%")),
				cb.like(root.get("burmese"), data.get().concat("%"))
			);
	}
	
	private Township entity(TownshipForm data) {
		var entity = new Township();
		entity.setName(data.name());
		entity.setBurmese(data.burmese());
		entity.setDivision(divRepo.findById(data.division()).orElseThrow(EntityNotFoundException::new));
		return entity;
	}

}
