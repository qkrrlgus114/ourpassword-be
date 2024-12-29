package com.park.ourpassword.domain.exception;

import com.park.ourpassword.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ApiResponse> getException(BaseException e) {
        return ResponseEntity.status(e.getStatus()).body(ApiResponse.errorMessage(e.getExceptionMessage()));
    }
}
