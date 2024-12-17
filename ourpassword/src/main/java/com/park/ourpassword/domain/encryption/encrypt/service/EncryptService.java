package com.park.ourpassword.domain.encryption.encrypt.service;

import org.springframework.stereotype.Service;

import com.park.ourpassword.domain.encryption.encrypt.repository.EncryptRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncryptService {

	private final EncryptRepository encryptRepository;

	/**
	 * 누적 암호화 횟수 가져오기
	 * */
	public int totalEncryptCount() {
		return encryptRepository.totalEncryptCount();
	}
}
