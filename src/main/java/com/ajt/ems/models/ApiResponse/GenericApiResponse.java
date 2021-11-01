package com.ajt.ems.models.ApiResponse;

import org.springframework.http.HttpStatus;

public class GenericApiResponse<T> {
	public String message;
	public T payload;
	public HttpStatus status;
	public Integer code;

	public GenericApiResponse(String message, T payload, HttpStatus status) {
		this.message = message;
		this.payload = payload;
		this.status = status;
		this.code = status.value();
	}
}
