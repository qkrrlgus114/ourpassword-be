package com.park.ourpassword.domain.encryption.encrypt.dto.request;

import com.park.ourpassword.domain.encryption.module.EncryptModuleEnum;

public record DecryptRequestDTO(
        String key,
        String encryptedValue,
        EncryptModuleEnum decryptModule
) {
}
