package com.park.ourpassword.domain.encryption.module;

import lombok.Getter;

/**
 * 자바에서 사용되는 다양한 암호 모듈 enum
 * */
@Getter
public enum EncryptModuleEnum {
	AES_128("AES-128", "AES-128"),
	AES_192("AES-192", "AES-192"),
	AES_256("AES-256", "AES-256"),
	RSA("RSA", "RSA"),
	SHA_256("SHA-256", "SHA-256"),
	SHA_512("SHA-512", "SHA-512"),
	BCrypt("BCrypt", "BCrypt");

	private String engName;
	private String korName;

	EncryptModuleEnum(String engName, String korName) {
		this.engName = engName;
		this.korName = korName;
	}
}
