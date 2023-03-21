package com.jdc.balance.model;

import jakarta.persistence.EntityNotFoundException;

public interface Exceptions {

	static<ID extends Number> EntityNotFoundException entityNotFoundException(String entity, ID id) {
		return new EntityNotFoundException("There is no %s with id %s.".formatted(entity, id));
	}
}
