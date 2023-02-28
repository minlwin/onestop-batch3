package com.jdc.location.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.api.utils.DataNotFoundException;
import com.jdc.location.entity.Township;
import com.jdc.location.repo.TownshipRepo;

@Service
@Transactional(readOnly = true)
public class TownshipService {
	
	@Autowired
	private TownshipRepo repo;

	public Township findById(int id) {
		return repo.findById(id).orElseThrow(() -> new DataNotFoundException("There is no data with id %d.".formatted(id)));
	}

	@Transactional
	public Township create(Township data) {
		return repo.save(data);
	}

	@Transactional
	public Township update(int id, Township data) {
		var entity = findById(id);
		entity.setName(data.getName());
		entity.setBurmese(data.getBurmese());
		return repo.save(entity);
	}

	public List<Township> search(Optional<Integer> division, Optional<String> keyword) {
		return repo.findAll(whichDivision(division).and(whichKeyword(keyword)));
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
	

}
