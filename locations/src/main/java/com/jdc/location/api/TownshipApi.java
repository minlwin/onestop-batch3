package com.jdc.location.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.api.form.TownshipForm;
import com.jdc.location.entity.Township;
import com.jdc.location.service.TownshipService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("township")
public class TownshipApi {
	
	@Autowired
	private TownshipService service;

	@GetMapping
	List<Township> search(
			@RequestParam Optional<Integer> type, 
			@RequestParam Optional<Integer> division, 
			@RequestParam Optional<String> keyword) {
		return service.search(type, division, keyword.filter(StringUtils::hasLength));
	}
	
	@GetMapping("{id}")
	Township findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	Township create(@RequestBody @Valid TownshipForm data, BindingResult result) {
		return service.create(data);
	}
	
	@PutMapping("{id}")
	Township update(@PathVariable int id, @RequestBody @Valid TownshipForm data, BindingResult result) {
		return service.update(id, data);
	}
	
}
