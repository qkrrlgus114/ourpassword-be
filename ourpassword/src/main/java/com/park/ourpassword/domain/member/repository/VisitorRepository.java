package com.park.ourpassword.domain.member.repository;

import com.park.ourpassword.domain.member.entity.VisitorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VisitorRepository extends JpaRepository<VisitorHistory, Long> {

    // 총 방문자 수 조회 쿼리
    @Query("SELECT COUNT(*) FROM VisitorHistory ")
    long totalVisitedCount();

    // 하루 방문자 수 조회 쿼리
    @Query("SELECT COUNT(*) FROM VisitorHistory WHERE accessAt BETWEEN :startDate AND :endDate")
    long dailyVisitedCount(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
