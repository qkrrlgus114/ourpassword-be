package com.park.ourpassword.domain.encryption.encrypt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.ourpassword.domain.encryption.encrypt.service.EncryptService;
import com.park.ourpassword.util.config.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class EncryptController {

	private final EncryptService encryptService;

	/**
	 * 누적 암호화 횟수 가져오는 API
	 * */
	@GetMapping("/encrypt-all")
	public ResponseEntity<ApiResponse> getTotalEncryptCount() {
		int totalEncryptCount = encryptService.totalEncryptCount();

		return ResponseEntity.ok(ApiResponse.successDataMessage(totalEncryptCount, ""));
	}
	
}
