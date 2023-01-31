package com.hupi.project.aop;

import com.hupi.project.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        /** 获取token */
        String token = request.getHeader("token");
        if (null == token) {
            log.info("token is null");
            response.setStatus(403);
            return false;
        }

        /** 检查token是否过期 */
        boolean isExpiration = JwtUtil.isTokenExpiration(token);
        if (isExpiration) {
            log.error("token is expiration");
            response.setStatus(403);
            return false;
        }

        return true;
    }
}