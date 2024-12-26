package com.park.ourpassword.domain.encryption.encrypt.repository;

import com.park.ourpassword.domain.encryption.encrypt.entity.DecryptHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecryptHistoryRepository extends JpaRepository<DecryptHistory, Long> {
}
