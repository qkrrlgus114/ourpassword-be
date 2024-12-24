package com.park.ourpassword.domain.member.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ourpassword.domain.member.repository.LoginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginService {

	private final LoginRepository loginRepository;

	/**
	 * 현재까지 누적 방문자 조회하는 메서드
	 * */
	@Transactional(readOnly = true)
	public long totalVisitedCount() {
		return loginRepository.totalVisitedCount();
	}

	/**
	 * 오늘 하루 방문자 수 조회하는 메서드
	 * */
	@Transactional(readOnly = true)
	public long dailyVisitedCount() {
		LocalDateTime startDate = LocalDate.now().atStartOfDay();
		LocalDateTime endDate = LocalDate.now().atTime(23, 59, 59, 999999);
		return loginRepository.dailyVisitedCount(startDate, endDate);
	}
}
