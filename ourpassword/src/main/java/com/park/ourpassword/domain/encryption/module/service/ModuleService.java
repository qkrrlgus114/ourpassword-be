package com.park.ourpassword.domain.encryption.module.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.park.ourpassword.domain.encryption.module.dto.response.ModuleDTO;
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
	public List<ModuleDTO> getModuleList() {
		List<EncryptModule> encryptModuleList = moduleRepository.findAll();
		List<ModuleDTO> moduleDTOList = new ArrayList<>();

		encryptModuleList.stream()
			.forEach(module -> {
				ModuleDTO moduleDTO = new ModuleDTO(module.getId(), module.getModule().getEngName(),
					module.getModule().getKorName());
				moduleDTOList.add(moduleDTO);
			});

		return moduleDTOList;
	}
}
