package com.hupi.project.aop;

import com.hupi.project.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        /** 获取token */
        String token = request.getHeader("authorization");
        Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-:._~+/]+=*)$", Pattern.CASE_INSENSITIVE);


        if (!StringUtils.startsWithIgnoreCase(token, "Bearer")) {
            log.info("authorization is null");
            response.setStatus(403);
            return false;
        }
        Matcher matcher = authorizationPattern.matcher(token);
        if (!matcher.matches()) {
            log.info("Bearer token is malformed");
            response.setStatus(403);
            return false;
        }
        token = matcher.group("token");
        System.out.println(token);

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