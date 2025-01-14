package com.park.ourpassword.domain.encryption.module;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 자바에서 사용되는 다양한 암호 모듈 enum
 * */
@Getter
public enum EncryptModuleEnum {
	AES_128,
	AES_192,
	AES_256,
	RSA,
	SHA_256,
	SHA_512,
	BCrypt,
	MD5,
	SHA1,
	SHA224,
	SHA256,
	SHA386,
	SHA512;
}
