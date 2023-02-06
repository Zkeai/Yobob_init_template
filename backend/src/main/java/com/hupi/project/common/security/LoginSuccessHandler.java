package com.hupi.project.common.security;

import cn.hutool.json.JSONUtil;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String account = authentication.getName();
        String token = JwtUtil.createToken(account);
        Map<String,Object> data = new ConcurrentHashMap<>();
        data.put("token",token);


        outputStream.write(JSONUtil.toJsonStr( ResultUtils.success(data)).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
