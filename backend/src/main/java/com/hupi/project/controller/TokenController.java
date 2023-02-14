package com.hupi.project.controller;

import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.entity.CheckResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.hupi.project.util.JwtUtil.validateJWT;

@RestController
@RequestMapping("/token")
public class TokenController {
    @GetMapping("/authentication")
    public CheckResult authentication(HttpServletRequest request) {
        String token = request.getHeader("authorization").replace("Bearer ", "");
        if(token.equals("null")|| token.equals("")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"token 不能为空");
        }
        return validateJWT(token);
    }
}
