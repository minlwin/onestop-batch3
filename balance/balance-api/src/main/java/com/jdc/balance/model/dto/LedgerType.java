package com.jdc.balance.model.dto;

import java.util.Arrays;

public enum LedgerType {
	Credit, Debit;
	
	public static boolean validateName(String name) {
		return Arrays.stream(values())
				.map(a -> a.name()).toList().contains(name);
	}
}
