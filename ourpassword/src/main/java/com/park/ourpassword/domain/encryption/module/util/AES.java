package com.park.ourpassword.domain.encryption.module.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * AES 암호화 알고리즘
 * (AES-128, AES-192, AES-256)
 *
 * 128 -> 128비트, 16바이트
 * 192 -> 192비트, 24바이트
 * 256 -> 256비트, 32바이트
 *
 * key의 길이만 다르게 설정한다.
 * */
@Slf4j
public class AES {

	public static EncryptResponseDTO encrypt(String key, String plainValue, boolean iv) {
		try {
			byte[] ivBytes = new byte[16];
			if (iv) {
				ivBytes = generateRandomIv();
			}

			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			if (iv) {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			}
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			byte[] encrypted = cipher.doFinal(plainValue.getBytes());

			return EncryptResponseDTO.builder()
				.encryptedValue(Base64.getEncoder().encodeToString(encrypted))
				.iv(Base64.getEncoder().encodeToString(ivBytes)).build();
		} catch (Exception e) {
			throw new RuntimeException("AES encryption failed", e);
		}
	}

	public static String decrypt(String key, String plainValue, boolean iv) {
		try {
			byte[] ivBytes = new byte[16];
			if (iv) {
				ivBytes = generateRandomIv();
			}

			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			if (iv) {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			}
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			byte[] encrypted = cipher.doFinal(plainValue.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			throw new RuntimeException("AES encryption failed", e);
		}
	}

	/**
	 * IV 생성 메서드
	 * */
	private static byte[] generateRandomIv() {
		byte[] ivBytes = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(ivBytes);

		return ivBytes;
	}
}
