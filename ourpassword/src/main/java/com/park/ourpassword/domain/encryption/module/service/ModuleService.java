package com.park.ourpassword.domain.encryption.module.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ourpassword.domain.encryption.module.dto.response.ModuleResponseDTO;
import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;
import com.park.ourpassword.domain.encryption.module.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModuleService {

	private final ModuleRepository moduleRepository;

	/*
	 * 모든 암호화 모듈을 가져오는 메서드
	 * */
	@Transactional(readOnly = true)
	public List<ModuleResponseDTO> getModuleList() {
		return  moduleRepository.findAll().stream()
				.map(ModuleResponseDTO::of)
				.toList();
	}
}
