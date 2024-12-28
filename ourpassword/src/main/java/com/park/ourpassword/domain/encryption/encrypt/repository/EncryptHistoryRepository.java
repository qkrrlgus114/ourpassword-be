package com.park.ourpassword.domain.encryption.encrypt.repository;

import com.park.ourpassword.domain.encryption.encrypt.entity.EncryptHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EncryptHistoryRepository extends JpaRepository<EncryptHistory, Long> {

    // 누적 암호화 횟수 가져오기
    @Query("SELECT COUNT(*) FROM EncryptHistory")
    int totalEncryptCount();

    // 하루 암호화 횟수 가져오기
    @Query("SELECT COUNT(*) FROM EncryptHistory WHERE accessAt BETWEEN :startDate AND :endDate")
    int todayEncryptCount(@Param("startDate") LocalDateTime startDate,
                          @Param("endDate") LocalDateTime endDate);
}
