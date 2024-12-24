package com.park.ourpassword.domain.member.schedule;

import com.park.ourpassword.domain.member.dto.cache.VisitorCacheInfo;
import com.park.ourpassword.domain.member.entity.VisitorHistory;
import com.park.ourpassword.domain.member.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VisitorScheduler {

    private final CacheManager cacheManager;
    private final VisitorRepository visitorRepository;

    private static final String VISITOR_CACHE = "visitorCache";
    private static final String VISITOR_LIST = "visitorList";

    @Scheduled(fixedDelay = 10000)
    public void saveVisitorCache(){
        log.info("visitorCache Scheduler start");
        Cache cache = cacheManager.getCache(VISITOR_CACHE);
        if(cache == null) return;

        List<VisitorCacheInfo> visitorCacheInfoList = cache.get(VISITOR_LIST, List.class);
        if(visitorCacheInfoList == null || visitorCacheInfoList.isEmpty()) return;

        List<VisitorHistory> visitorHistories = visitorCacheInfoList.stream()
                .map(VisitorCacheInfo::toEntity).toList();
        visitorRepository.saveAll(visitorHistories);

        cache.evict(VISITOR_LIST);

        log.info("visitorCache Scheduler end");
    }
}
