package com.park.ourpassword.domain.encryption.module.service;

import com.park.ourpassword.domain.encryption.module.dto.response.ModuleResponseDTO;
import com.park.ourpassword.domain.encryption.module.repository.ModuleRepository;
import com.park.ourpassword.domain.member.dto.cache.VisitorCacheInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CacheManager cacheManager;

    private static final String VISITOR_CACHE = "visitorCache";
    private static final String VISITOR_LIST = "visitorList";

    /*
     * 모든 암호화 모듈을 가져오는 메서드
     * */
    @Transactional(readOnly = true)
    public List<ModuleResponseDTO> getModuleList(HttpServletRequest request) {
        addVisitorCache(request);

        return moduleRepository.findAll().stream()
                .map(ModuleResponseDTO::of)
                .toList();
    }

    // 캐시에 방문자 저장하는 메서드
    private void addVisitorCache(HttpServletRequest request) {
        Cache cache = cacheManager.getCache(VISITOR_CACHE);

        if (cache == null) return;

        List<VisitorCacheInfo> visitorList = cache.get(VISITOR_LIST, List.class);
        if (visitorList == null) {
            visitorList = new ArrayList<>();
        }

        visitorList.add(VisitorCacheInfo.of(request.getRemoteAddr()));
        cache.put(VISITOR_LIST, visitorList);
    }
}
