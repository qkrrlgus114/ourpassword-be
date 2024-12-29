package com.park.ourpassword.domain.member.controller;

import com.park.ourpassword.domain.member.service.MemberService;
import com.park.ourpassword.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    /**
     * 누적 방문자 수 조회하는 API
     */
    @GetMapping("/visited-all")
    public ResponseEntity<ApiResponse<Long>> getTotalVisitedCount() {
        return ResponseEntity.ok(ApiResponse.successDataMessage(memberService.totalVisitedCount(), ""));
    }

    /**
     * 오늘의 방문자 수를 조회하는 API
     */
    @GetMapping("/visited-today")
    public ResponseEntity<ApiResponse<Long>> getDailyVisitedCount() {
        return ResponseEntity.ok(ApiResponse.successDataMessage(memberService.dailyVisitedCount(), ""));
    }
}
