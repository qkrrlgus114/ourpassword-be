package com.park.ourpassword.domain.encryption.module.util;

import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.encryption.module.EncryptModuleEnum;
import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Base64;

@Slf4j
public class SHA {

    public static EncryptResponseDTO encrypt(String value, EncryptModuleEnum encryptModuleEnum) {
        try {
            log.info("SHA 모듈 시작 : {}", encryptModuleEnum.name());
            MessageDigest sha = MessageDigest.getInstance(encryptModuleEnum.name().replace("_", ""));

            sha.update(value.getBytes());

            String encryptedValue = Base64.getEncoder().encodeToString(sha.digest());

            return EncryptResponseDTO.builder()
                    .encryptedValue(encryptedValue)
                    .build();
        } catch (Exception e) {
            throw new CommonException(EncryptExceptionInfo.ERROR);
        }
    }
}
