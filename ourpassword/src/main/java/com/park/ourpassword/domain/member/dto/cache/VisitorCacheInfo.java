package com.park.ourpassword.domain.member.dto.cache;


import com.park.ourpassword.domain.member.entity.VisitorHistory;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 방문자 수를 캐싱하기 위해 사용하는 DTO
 */
public class VisitorCacheInfo {

    private LocalDateTime accessAt;
    private String ip;

    @Builder
    public VisitorCacheInfo(LocalDateTime accessAt, String ip) {
        this.accessAt = accessAt;
        this.ip = ip;
    }

    public static VisitorCacheInfo of(String ip) {
        return VisitorCacheInfo.builder()
                .accessAt(LocalDateTime.now())
                .ip(ip).build();

    }

    public static VisitorHistory toEntity(VisitorCacheInfo visitorCacheInfo) {
        return VisitorHistory.builder()
                .accessAt(visitorCacheInfo.accessAt)
                .ip(visitorCacheInfo.ip).build();
    }

}
