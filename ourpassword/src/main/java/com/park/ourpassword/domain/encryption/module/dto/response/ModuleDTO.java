package com.park.ourpassword.domain.encryption.module.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModuleDTO {

	private Long id;
	private String engName;
	private String korName;

	public ModuleDTO(Long id, String engName, String korName) {
		this.id = id;
		this.engName = engName;
		this.korName = korName;
	}
}
