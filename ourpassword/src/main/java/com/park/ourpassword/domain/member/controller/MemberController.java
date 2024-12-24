package com.park.ourpassword.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.ourpassword.domain.member.service.MemberService;
import com.park.ourpassword.util.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	/**
	 * 누적 방문자 수 조회하는 API
	 * */
	@GetMapping("/visited-all")
	public ResponseEntity<ApiResponse> getTotalVisitedCount() {
		long cnt = memberService.totalVisitedCount();

		return ResponseEntity.ok(ApiResponse.successDataMessage(cnt, ""));
	}

	/**
	 * 오늘의 방문자 수를 조회하는 API
	 * */
	@GetMapping("/visited-today")
	public ResponseEntity<ApiResponse> getDailyVisitedCount() {
		long cnt = memberService.dailyVisitedCount();

		return ResponseEntity.ok(ApiResponse.successDataMessage(cnt, ""));
	}
}
