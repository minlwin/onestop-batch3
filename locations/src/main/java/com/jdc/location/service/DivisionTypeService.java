package com.jdc.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.api.utils.DataNotFoundException;
import com.jdc.location.entity.DivisionType;
import com.jdc.location.repo.DivisionTypeRepo;

import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
public class DivisionTypeService {
	
	@Autowired
	private DivisionTypeRepo repo;

	public List<DivisionType> search() {
		return repo.findAll();
	}

	public DivisionType findById(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new DataNotFoundException("There is no data with id %d.".formatted(id)));
	}

	@Transactional
	public DivisionType save(@Valid DivisionType dto) {
		return repo.save(dto);
	}

	@Transactional
	public DivisionType update(int id, @Valid DivisionType dto) {
		var entity = findById(id);
		entity.setName(dto.getName());
		entity.setBurmese(dto.getBurmese());
		return repo.save(entity);
	}

}
