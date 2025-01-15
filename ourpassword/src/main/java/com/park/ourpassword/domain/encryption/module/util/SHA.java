package com.park.ourpassword.domain.encryption.module.util;

import com.park.ourpassword.domain.encryption.encrypt.dto.request.EncryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Base64;

@Slf4j
public class SHA extends BaseEncrypt {

    public static EncryptResponseDTO encrypt(EncryptRequestDTO encryptRequestDTO) {
        try {
            log.info("SHA 모듈 시작 : {}", encryptRequestDTO.encryptModule().name());
            MessageDigest sha = MessageDigest.getInstance(encryptRequestDTO.encryptModule().name().replace("_", ""));

            sha.update(encryptRequestDTO.value().getBytes());

            return EncryptResponseDTO.builder()
                    .encryptedValue(
                            encryptRequestDTO.returnType().equals("base64") ?
                                    Base64.getEncoder().encodeToString(sha.digest()) :
                                    byteToHexString(sha.digest()))
                    .build();
        } catch (Exception e) {
            throw new CommonException(EncryptExceptionInfo.ERROR);
        }
    }


}
