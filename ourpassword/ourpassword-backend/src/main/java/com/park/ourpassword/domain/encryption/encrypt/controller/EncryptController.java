package com.park.ourpassword.domain.encryption.encrypt.controller;

import com.park.ourpassword.domain.encryption.encrypt.dto.request.DecryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.request.EncryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.DecryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.service.EncryptService;
import com.park.ourpassword.util.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class EncryptController {

    private final EncryptService encryptService;

    /**
     * 누적 암호화 횟수 가져오는 API
     */
    @GetMapping("/encrypt-all")
    public ResponseEntity<ApiResponse<Integer>> getTotalEncryptCount() {
        return ResponseEntity.ok(ApiResponse.successDataMessage(encryptService.totalEncryptCount(), ""));
    }

    /**
     * 하루 암호화 횟수 가져오는 API
     */
    @GetMapping("/encrypt-today")
    public ResponseEntity<ApiResponse<Integer>> getTodayEncryptCount() {
        return ResponseEntity.ok(ApiResponse.successDataMessage(encryptService.todayEncryptCount(), ""));
    }

    /**
     * 값 암호화 진행하는 API
     */
    @PostMapping("/encrypt")
    public ResponseEntity<ApiResponse<EncryptResponseDTO>> encryptValue(@RequestBody EncryptRequestDTO encryptRequestDTO,
                                                                        HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.successDataMessage(encryptService.encryptValue(encryptRequestDTO, request), "암호화 완료"));
    }

    /**
     * 값 복호화 진행하는 API
     */
    @PostMapping("/decrypt")
    public ResponseEntity<ApiResponse<DecryptResponseDTO>> decryptValue(@RequestBody DecryptRequestDTO decryptRequestDTO,
                                                                        HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.successDataMessage(encryptService.decryptValue(decryptRequestDTO, request), "복호화 완료"));
    }

}
