package com.park.ourpassword.domain.exception.domain;

import com.park.ourpassword.domain.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EncryptExceptionInfo implements BaseException {
    NOT_FOUND_VALUE("ENC-001", HttpStatus.BAD_REQUEST, "내용이 존재하지 않습니다."),
    AES_256_ERROR_LENGTH("ENC-002", HttpStatus.BAD_REQUEST, "[AES_256] KEY의 길이는 32자여야 합니다."),
    NOT_FOUND_MODULE("ENC-003", HttpStatus.BAD_REQUEST, "지원 가능한 모듈이 아닙니다."),
    ERROR("ENC-999", HttpStatus.INTERNAL_SERVER_ERROR, "암호화 진행 중 문제가 발생했습니다.");

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
