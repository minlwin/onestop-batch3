package com.jdc.location.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.api.utils.DataNotFoundException;
import com.jdc.location.entity.Division;
import com.jdc.location.repo.DivisionRepo;

@Service
@Transactional(readOnly = true)
public class DivisionService {
	
	@Autowired
	private DivisionRepo repo;

	public Division findById(int id) {
		return repo.findById(id).orElseThrow(() -> new DataNotFoundException("There is no data with id %d.".formatted(id)));
	}

	@Transactional
	public Division create(Division data) {
		return repo.save(data);
	}

	@Transactional
	public Division update(int id, Division data) {
		var entity = findById(id);
		entity.setName(data.getName());
		entity.setBurmese(data.getBurmese());
		entity.setCapital(data.getCapital());
		return repo.save(entity);
	}

	public List<Division> search(Optional<Integer> type, Optional<String> keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
