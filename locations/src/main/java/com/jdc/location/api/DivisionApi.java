package com.jdc.location.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.api.form.DivisionForm;
import com.jdc.location.entity.Division;
import com.jdc.location.service.DivisionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("division")
public class DivisionApi {
	
	@Autowired
	private DivisionService service;

	@GetMapping
	List<Division> search(
			@RequestParam Optional<Integer> type, 
			@RequestParam Optional<String> keyword) {
		return service.search(type, keyword);
	}
	
	@GetMapping("{id}")
	Division findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	Division create(@RequestBody @Valid DivisionForm data, BindingResult result) {
		return service.create(data);
	}
	
	@PutMapping("{id}")
	Division update(@PathVariable int id, @RequestBody @Valid DivisionForm data, BindingResult result) {
		return service.update(id, data);
	}
}
