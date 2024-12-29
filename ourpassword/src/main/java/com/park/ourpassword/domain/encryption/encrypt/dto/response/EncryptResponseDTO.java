package com.park.ourpassword.domain.encryption.encrypt.dto.response;

import lombok.Builder;

@Builder
public record EncryptResponseDTO(
        String encryptedValue
) {
}
