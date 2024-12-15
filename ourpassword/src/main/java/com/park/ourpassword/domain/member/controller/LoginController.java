package com.park.ourpassword.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.ourpassword.domain.member.service.LoginService;
import com.park.ourpassword.util.config.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;

	/**
	 * 누적 방문자 수 조회하는 API
	 * */
	@GetMapping("/visited-all")
	public ResponseEntity<ApiResponse> getTotalVisitedCount() {
		long cnt = loginService.totalVisitedCount();

		return ResponseEntity.ok(ApiResponse.successDataMessage(cnt, ""));
	}

	/**
	 * 오늘의 방문자 수를 조회하는 API
	 * */
	@GetMapping("/visited-today")
	public ResponseEntity<ApiResponse> getDailyVisitedCount() {
		long cnt = loginService.dailyVisitedCount();

		return ResponseEntity.ok(ApiResponse.successDataMessage(cnt, ""));
	}
}
