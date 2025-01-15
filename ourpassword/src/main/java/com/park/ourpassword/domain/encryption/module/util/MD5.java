package com.park.ourpassword.domain.encryption.module.util;

import com.park.ourpassword.domain.encryption.encrypt.dto.request.EncryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * 128비트의 고정 길이로 값을 반환하는 알고리즘
 * <p>
 * 단뱡항 알고리즘
 */
@Slf4j
public class MD5 extends BaseEncrypt {

    private static final String MD_5 = "MD5";

    public static EncryptResponseDTO encrypt(EncryptRequestDTO encryptRequestDTO) {
        try {
            log.info("MD5 암호화 실행");

            MessageDigest md5 = MessageDigest.getInstance(MD_5);

            md5.update(encryptRequestDTO.value().getBytes());

            return EncryptResponseDTO.builder()
                    .encryptedValue(
                            encryptRequestDTO.returnType().equals("base64") ?
                                    Base64.getEncoder().encodeToString(md5.digest()) :
                                    byteToHexString(md5.digest()))
                    .build();
        } catch (Exception e) {
            throw new CommonException(EncryptExceptionInfo.ERROR);
        }
    }
}
