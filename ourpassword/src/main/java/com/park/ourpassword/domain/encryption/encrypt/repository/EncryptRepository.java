package com.park.ourpassword.domain.encryption.encrypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.park.ourpassword.domain.encryption.encrypt.entity.EncryptHistory;

public interface EncryptRepository extends JpaRepository<EncryptHistory, Long> {

	@Query("SELECT COUNT(*) FROM EncryptHistory")
	int totalEncryptCount();
}
