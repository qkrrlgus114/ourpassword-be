package com.park.ourpassword.domain.encryption.module.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * AES128은 128비트의 키를 사용한다.
 *
 * 128비트 = 16바이트
 *
 * 즉 16자리의 키가 필요하다.
 * */
@Slf4j
public class AES128 {

	public static String encrypt(byte[] target, byte[] key) {
		// 키의 길이는 16자여야 함
		if (key.length != 16) {
			throw new CommonException(EncryptExceptionInfo.AES_128_ERROR_LENGTH);
		}

		SecretKeySpec keySpec = null;

		keySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			log.error("[AES_128] Invalid Algorithm, {}", e.getMessage());
			return null;
		} catch (NoSuchPaddingException e) {
			log.error("[AES_128] error, {}", e.getMessage());
			return null;
		}

		try {
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		} catch (InvalidKeyException e) {
			log.error("[AES_128] InvalidKey, {}", e.getMessage());
			return null;
		}

		try {
			Base64.Encoder encoder = Base64.getEncoder();
			return new String(encoder.encode(cipher.doFinal(target)));
		} catch (IllegalBlockSizeException e) {
			log.error("[AES_128] IllegalBlockSize, {}", e.getMessage());
		} catch (BadPaddingException e) {
			log.error("[AES_128] BadPadding, {}", e.getMessage());
		}
		return null;
	}
}
