package com.park.ourpassword.domain.encryption.encrypt.service;

import com.park.ourpassword.domain.encryption.encrypt.dto.request.DecryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.request.EncryptRequestDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.DecryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.dto.response.EncryptResponseDTO;
import com.park.ourpassword.domain.encryption.encrypt.entity.DecryptHistory;
import com.park.ourpassword.domain.encryption.encrypt.entity.EncryptHistory;
import com.park.ourpassword.domain.encryption.encrypt.repository.DecryptHistoryRepository;
import com.park.ourpassword.domain.encryption.encrypt.repository.EncryptHistoryRepository;
import com.park.ourpassword.domain.encryption.module.entity.EncryptModule;
import com.park.ourpassword.domain.encryption.module.repository.ModuleRepository;
import com.park.ourpassword.domain.encryption.module.util.AES;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncryptService {

    private final EncryptHistoryRepository encryptHistoryRepository;
    private final DecryptHistoryRepository decryptHistoryRepository;
    private final ModuleRepository moduleRepository;

    /**
     * 누적 암호화 횟수 가져오기
     */
    @Transactional(readOnly = true)
    public int totalEncryptCount() {
        return encryptHistoryRepository.totalEncryptCount();
    }

    /**
     * 하루 암호화 횟수 가져오기
     */
    @Transactional(readOnly = true)
    public int todayEncryptCount() {
        LocalDateTime startDate = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = LocalDate.now().atTime(23, 59, 59, 999999);
        return encryptHistoryRepository.todayEncryptCount(startDate, endDate);
    }

    /**
     * 암호화 진행하는 메서드
     */
    @Transactional
    public EncryptResponseDTO encryptValue(EncryptRequestDTO encryptRequestDTO, HttpServletRequest request) {
        EncryptResponseDTO encrypt = AES.encrypt(encryptRequestDTO.key(), encryptRequestDTO.value());

        List<EncryptModule> encryptModuleList = moduleRepository.findAll();
        EncryptModule encryptModule = encryptModuleList.stream()
                .filter(module -> encryptRequestDTO.encryptModule() == module.getModule())
                .findFirst()
                .get();

        // 암호화 기록 생성
        EncryptHistory encryptHistory = EncryptHistory.builder()
                .accessAt(LocalDateTime.now())
                .rawPassword(encryptRequestDTO.value())
                .secretKey(encryptRequestDTO.key())
                .encryptedPassword(encrypt.encryptedValue())
                .ip(request.getRemoteAddr())
                .encryptModule(encryptModule).build();
        encryptHistoryRepository.save(encryptHistory);

        return encrypt;
    }

    /**
     * 복호화 진행하는 메서드
     */
    @Transactional
    public DecryptResponseDTO decryptValue(DecryptRequestDTO decryptRequestDTO, HttpServletRequest request) {
        DecryptResponseDTO decryptResponseDTO =
                AES.decrypt(decryptRequestDTO.key(), decryptRequestDTO.encryptedValue());

        List<EncryptModule> encryptModuleList = moduleRepository.findAll();
        EncryptModule encryptModule = encryptModuleList.stream()
                .filter(module -> decryptRequestDTO.decryptModule() == module.getModule())
                .findFirst()
                .get();

        DecryptHistory decryptHistory = DecryptHistory.builder()
                .ip(request.getRemoteAddr())
                .accessAt(LocalDateTime.now())
                .secretKey(decryptRequestDTO.key())
                .encryptedPassword(decryptRequestDTO.encryptedValue())
                .decryptedPassword(decryptResponseDTO.decryptedValue())
                .encryptModule(encryptModule).build();
        decryptHistoryRepository.save(decryptHistory);

        return decryptResponseDTO;
    }
}
