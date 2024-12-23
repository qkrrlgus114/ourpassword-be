package com.park.ourpassword.domain.exception.domain;

import org.springframework.http.HttpStatus;

import com.park.ourpassword.domain.exception.BaseException;

import lombok.Getter;

@Getter
public enum EncryptExceptionInfo implements BaseException {
	AES_128_ERROR_LENGTH("ENC-001", HttpStatus.BAD_REQUEST, "[AES_128] KEY의 길이는 16자여야 합니다."),
	AES_256_ERROR_LENGTH("ENC-002", HttpStatus.BAD_REQUEST, "[AES_256] KEY의 길이는 16자여야 합니다.");

	private String code;
	private HttpStatus httpStatus;
	private String message;

	EncryptExceptionInfo(String code, HttpStatus httpStatus, String message) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	@Override
	public String getExceptionMessage() {
		return message;
	}

	@Override
	public String getLog() {
		return null;
	}

	@Override
	public HttpStatus getStatus() {
		return httpStatus;
	}
}
