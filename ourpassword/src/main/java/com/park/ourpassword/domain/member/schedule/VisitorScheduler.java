package com.park.ourpassword.domain.member.schedule;

import com.park.ourpassword.domain.member.schedule.service.VisitorSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VisitorScheduler {

    private final VisitorSchedulerService visitorSchedulerService;

    /**
     * 1분마다 방문자수 저장하는 스케줄러 동작
     */
    @Scheduled(fixedDelay = 20001)
    public void saveVisitorCache() {
        visitorSchedulerService.saveVisitorCacheData();
    }
}
