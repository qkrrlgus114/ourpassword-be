package com.park.ourpassword.domain.encryption.module.dto.response;

import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModuleResponseDTO {

	private Long id;
	private String name;

	@Builder
	public ModuleResponseDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ModuleResponseDTO of(EncryptModule encryptModule) {
		return ModuleResponseDTO.builder()
				.id(encryptModule.getId())
				.name(encryptModule.getModule().name()).build();
	}
}
