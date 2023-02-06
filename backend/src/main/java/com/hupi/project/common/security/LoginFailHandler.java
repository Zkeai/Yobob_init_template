package com.hupi.project.common.security;

import cn.hutool.json.JSONUtil;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String message = exception.getMessage();
        if(exception instanceof BadCredentialsException){
            message = "用户名或者密码错误";
        }


        outputStream.write(JSONUtil.toJsonStr( ResultUtils.error(403,message)).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();


    }
}
