package com.park.ourpassword.domain.encryption.encrypt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ourpassword.domain.encryption.encrypt.dto.request.EncryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.entity.EncryptHistory;
import com.park.ourpassword.domain.encryption.encrypt.repository.EncryptHistoryRepository;
import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;
import com.park.ourpassword.domain.encryption.module.repository.ModuleRepository;
import com.park.ourpassword.domain.encryption.module.util.AES;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncryptService {

	private final EncryptHistoryRepository encryptHistoryRepository;
	private final ModuleRepository moduleRepository;

	/**
	 * 누적 암호화 횟수 가져오기
	 * */
	@Transactional(readOnly = true)
	public int totalEncryptCount() {
		return encryptHistoryRepository.totalEncryptCount();
	}

	/**
	 * 암호화 진행하는 메서드
	 * */
	@Transactional
	public EncryptResponseDTO encryptValue(EncryptRequestDTO encryptRequestDTO, HttpServletRequest request) {
		EncryptResponseDTO encrypt = AES.encrypt(encryptRequestDTO.key(), encryptRequestDTO.value(),
			encryptRequestDTO.iv());

		List<EncryptModule> encryptModuleList = moduleRepository.findAll();
		EncryptModule encryptModule = encryptModuleList.stream()
			.filter(module -> encryptRequestDTO.encryptModule() == module.getModule())
			.findFirst()
			.get();

		// 암호화 기록 생성
		EncryptHistory encryptHistory = EncryptHistory.builder()
			.accessAt(LocalDateTime.now())
			.rawPassword(encryptRequestDTO.value())
			.secretKey(encryptRequestDTO.key())
			.encryptedPassword(encrypt.encryptedValue())
			.ip(request.getRemoteAddr())
			.encryptModule(encryptModule).build();
		encryptHistoryRepository.save(encryptHistory);

		return encrypt;
	}
}
