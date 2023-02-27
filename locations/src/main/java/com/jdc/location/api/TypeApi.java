package com.jdc.location.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.entity.DivisionType;
import com.jdc.location.service.DivisionTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("type")
public class TypeApi {
	
	@Autowired
	private DivisionTypeService service;

	@GetMapping
	List<DivisionType> search() {
		return service.search();
	}
	
	@GetMapping("{id}")
	DivisionType findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	DivisionType create(@RequestBody @Valid DivisionType dto, BindingResult result) {
		return service.save(dto);
	}
	
	@PutMapping("{id}")
	DivisionType update(@PathVariable int id, @RequestBody @Valid DivisionType dto, BindingResult result) {
		return service.update(id, dto);
	}
}
