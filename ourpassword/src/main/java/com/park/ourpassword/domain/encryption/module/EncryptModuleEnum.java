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
	SHA_224,
	SHA_256,
	SHA_386,
	SHA_512,
	BCrypt,
	MD_5,
	SHA_1;
}
