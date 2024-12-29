package com.park.ourpassword.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonException extends RuntimeException implements BaseException {

	private BaseException baseException;

	public CommonException(BaseException baseException) {
		this.baseException = baseException;
	}

	@Override
	public String getExceptionMessage() {
		return baseException.getExceptionMessage();
	}

	@Override
	public String getLog() {
		return baseException.getLog();
	}

	@Override
	public HttpStatus getStatus() {
		return baseException.getStatus();
	}
}
