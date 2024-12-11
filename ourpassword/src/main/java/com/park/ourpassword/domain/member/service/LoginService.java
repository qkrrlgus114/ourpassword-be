package com.park.ourpassword.domain.member.service;

import org.springframework.stereotype.Service;

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
	public long totalVisitedCount() {
		return loginRepository.totalVisitedCount();
	}
}
