package com.park.ourpassword.domain.encryption.module.util;

import com.park.ourpassword.domain.encryption.encrypt.dto.response.DecryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 암호화 알고리즘
 * (AES-256)
 * <p>
 * 256 -> 256비트, 32바이트
 */
@Slf4j
public class AES {

    private static final int KEY_LENGTH = 32;
    private static final int IV_LENGTH = 16;

    /**
     * AES 암호화
     */
    public static EncryptResponseDTO encrypt(String key, String plainValue) {
        try {
            if (key.length() != KEY_LENGTH) {
                throw new CommonException(EncryptExceptionInfo.AES_256_ERROR_LENGTH);
            }

            byte[] iv = generateRandomIv();

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            byte[] encrypted = cipher.doFinal(plainValue.getBytes());

            byte[] combined = new byte[IV_LENGTH + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, IV_LENGTH);
            System.arraycopy(encrypted, 0, combined, IV_LENGTH, encrypted.length);

            return EncryptResponseDTO.builder()
                    .encryptedValue(Base64.getEncoder().encodeToString(combined))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("AES encryption failed", e);
        }
    }

    public static DecryptResponseDTO decrypt(String key, String encryptedValue) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

            // 암호문 디코딩 및 IV 분리
            byte[] combined = Base64.getDecoder().decode(encryptedValue);
            byte[] iv = new byte[IV_LENGTH];
            byte[] encryptedBytes = new byte[combined.length - IV_LENGTH];
            System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
            System.arraycopy(combined, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

            // Cipher 설정
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // 복호화
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return DecryptResponseDTO.builder().decryptedValue(new String(decryptedBytes)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * IV 생성 메서드
     */
    private static byte[] generateRandomIv() {
        byte[] ivBytes = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);

        return ivBytes;
    }
}
