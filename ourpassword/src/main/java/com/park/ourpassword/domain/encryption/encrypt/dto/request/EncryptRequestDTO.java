package com.park.ourpassword.domain.encryption.encrypt.dto.request;

import com.park.ourpassword.domain.encryption.module.EncryptModuleEnum;
import lombok.Builder;

@Builder
public record EncryptRequestDTO(
        EncryptModuleEnum encryptModule,
        String key,
        String value
) {
}
