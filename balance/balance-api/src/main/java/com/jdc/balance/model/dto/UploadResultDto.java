package com.jdc.balance.model.dto;

public record UploadResultDto(
	boolean success,
	int size,
	String message
		) {

}
