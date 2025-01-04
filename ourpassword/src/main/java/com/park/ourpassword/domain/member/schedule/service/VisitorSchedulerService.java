package com.park.ourpassword.domain.member.schedule.service;

import com.park.ourpassword.domain.member.dto.cache.VisitorCacheInfo;
import com.park.ourpassword.domain.member.entity.VisitorHistory;
import com.park.ourpassword.domain.member.repository.VisitorJDBCRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VisitorSchedulerService {

    private final CacheManager cacheManager;
    private final VisitorJDBCRepository visitorJDBCRepository;

    private static final String VISITOR_CACHE = "visitorCache";
    private static final String VISITOR_LIST = "visitorList";

    /**
     * 방문자 캐싱 데이터 저장하는 메서드
     */
    @Transactional
    public void saveVisitorCacheData() {
        log.info("visitorCache Scheduler start");
        Cache cache = cacheManager.getCache(VISITOR_CACHE);
        if (cache == null) return;

        List<VisitorCacheInfo> visitorCacheInfoList = cache.get(VISITOR_LIST, List.class);
        if (visitorCacheInfoList == null || visitorCacheInfoList.isEmpty()) return;

        List<VisitorHistory> visitorHistories = visitorCacheInfoList.stream()
                .map(VisitorCacheInfo::toEntity).toList();

        visitorJDBCRepository.bulkInsert(visitorHistories);

        cache.evict(VISITOR_LIST);

        log.info("visitorCache Scheduler end");
    }
}

