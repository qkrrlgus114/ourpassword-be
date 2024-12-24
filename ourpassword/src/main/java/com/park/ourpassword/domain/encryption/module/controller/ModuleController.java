package com.park.ourpassword.domain.encryption.module.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.ourpassword.domain.encryption.module.dto.response.ModuleResponseDTO;
import com.park.ourpassword.domain.encryption.module.service.ModuleService;
import com.park.ourpassword.util.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ModuleController {

	private final ModuleService moduleService;

	/**
	 * 사용 가능한 암호화 모듈 리스트를 반환하는 API
	 * */
	@GetMapping("/modules")
	public ResponseEntity<ApiResponse> getEncryptModules() {
		List<ModuleResponseDTO> moduleList = moduleService.getModuleList();

		return ResponseEntity.ok(ApiResponse.successDataMessage(moduleList, ""));
	}

}
