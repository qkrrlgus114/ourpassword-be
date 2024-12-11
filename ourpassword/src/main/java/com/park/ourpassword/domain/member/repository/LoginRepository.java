package com.park.ourpassword.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.park.ourpassword.domain.member.entity.LoginHistory;

public interface LoginRepository extends JpaRepository<LoginHistory, Long> {

	@Query("SELECT COUNT(*) FROM LoginHistory")
	long totalVisitedCount();
}
