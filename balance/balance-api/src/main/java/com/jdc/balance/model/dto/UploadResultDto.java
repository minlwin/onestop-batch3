package com.jdc.balance.model.dto;

public record UploadResultDto(
	boolean success,
	int size,
	String message
		) {
	
	public static UploadResultDto success(int count) {
		return new UploadResultDto(true, count, "Successfully Uploaded.");
	}
	
	public static UploadResultDto fails(String message) {
		return new UploadResultDto(false, 0, message);
	}

}
