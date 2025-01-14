package com.park.ourpassword.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 리버스프록시 IP에서 실제 요청 IP 추출하는 유틸
 */
@Slf4j
public class IPUtil {

    public static String resolveArgument(HttpServletRequest request) {
        try {
            String clientIp = request.getHeader("X-Forwarded-For");
            if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
                // X-Forwarded-For 헤더가 존재할 경우, 첫 번째 IP만 사용
                return clientIp.split(",")[0].trim();
            }

            // 그 외의 경우 다른 헤더 확인
            clientIp = request.getHeader("Proxy-Client-IP");
            if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
                return clientIp;
            }

            clientIp = request.getHeader("WL-Proxy-Client-IP");
            if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
                return clientIp;
            }

            clientIp = request.getHeader("HTTP_CLIENT_IP");
            if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
                return clientIp;
            }

            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
            if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
                return clientIp;
            }

            // 마지막으로 직접 연결된 경우
            return request.getRemoteAddr();
        } catch (Exception e) {
            log.error("IP 추출 실패 : {}, message : {}", request.getRemoteAddr(), e.getMessage());
            return "0.0.0.0";
        }

    }
}
