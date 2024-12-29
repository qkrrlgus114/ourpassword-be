package com.park.ourpassword.domain.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {

	String getExceptionMessage();

	String getLog();

	HttpStatus getStatus();
}
