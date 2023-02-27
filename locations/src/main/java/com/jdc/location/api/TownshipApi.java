package com.jdc.location.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.entity.Township;

@RestController
@RequestMapping("township")
public class TownshipApi {

	@GetMapping
	List<Township> search(
			@RequestParam Optional<Integer> division, 
			@RequestParam Optional<String> keyword) {
		return null;
	}
}
