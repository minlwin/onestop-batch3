package com.jdc.balance.model;

import java.util.List;

public class BalanceAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> messages;

	public BalanceAppException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
