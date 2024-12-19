package com.park.ourpassword.domain.encryption.encrypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.park.ourpassword.domain.encryption.encrypt.entity.EncryptHistory;

public interface EncryptHistoryRepository extends JpaRepository<EncryptHistory, Long> {

	// 누적 암호화 횟수 가져오기
	@Query("SELECT COUNT(*) FROM EncryptHistory")
	int totalEncryptCount();
}
