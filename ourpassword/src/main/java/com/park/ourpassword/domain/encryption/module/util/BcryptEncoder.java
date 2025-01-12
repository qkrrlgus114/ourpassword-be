package com.park.ourpassword.domain.encryption.module.util;

import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.exception.CommonException;
import com.park.ourpassword.domain.exception.domain.EncryptExceptionInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 스프링 시큐리티에서 암호화를 위해 사용되는 모듈
 * 
 * BCryptPasswordEncoder 사용
 * */
@Slf4j
@RequiredArgsConstructor
@Component
public class BcryptEncoder {

    private final PasswordEncoder passwordEncoder;


    public EncryptResponseDTO encrypt(String value){
        try{
            if(value == null || value.isBlank()){
                throw new CommonException(EncryptExceptionInfo.NOT_FOUND_VALUE);
            }

            String encode = passwordEncoder.encode(value);

            return EncryptResponseDTO.builder()
                    .encryptedValue(encode).build();
        } catch (CommonException ce){
            log.error("에러");
            throw new CommonException(EncryptExceptionInfo.NOT_FOUND_VALUE);
        } catch (Exception e){
            throw new CommonException(EncryptExceptionInfo.ERROR);
        }
    }
}
