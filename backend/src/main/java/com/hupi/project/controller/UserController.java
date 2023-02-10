package com.hupi.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.DeleteRequest;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.dto.user.*;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.AdminVO;
import com.hupi.project.model.vo.UserRoleVO;
import com.hupi.project.model.vo.UserVO;
import com.hupi.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author zkeai
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    // region 登录相关

    /**
     * 用户注册
     *
     * @param userRegisterRequest  userRegisterRequest
     * @return id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }


    /**
     * 获取当前登录用户
     * @param request request
     * @return  UserVO
     */
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    // endregion

    // region 系统用户增删改查


    /**
     * 删除用户
     *
     * @param deleteRequest deleteRequest
     * @param request request
     * @return BaseResponse<Boolean>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }


    /**
     * 根据 id 获取用户
     *
     * @param id id
     * @param request request
     * @return UserVO
     */
    @GetMapping("/get")
    public BaseResponse<UserVO> getUserById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 获取用户列表
     *
     * @param userQueryRequest userQueryRequest
     * @param request request
     * @return List<UserVO>
     */
    @GetMapping("/list")
    public BaseResponse<List<UserVO>> listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);

        assert userQueryRequest != null;
        if(userQueryRequest.getCreateTime() != null){
            String time= userQueryRequest.getCreateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }
        if(userQueryRequest.getUpdateTime() != null){
            String time= userQueryRequest.getUpdateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }

        List<User> userList = userService.list(queryWrapper);
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }

    /**
     * 分页获取用户列表
     *
     * @param userQueryRequest userQueryRequest
     * @param request  request
     * @return Page<UserVO>
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<UserVO>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 1;
        User userQuery = new User();

        if (userQueryRequest != null) {
            //BeanUtils.copyProperties(userQueryRequest, userQuery);
            if(!Objects.equals(userQueryRequest.getUserName(), "")) {
                userQuery.setUserName(userQueryRequest.getUserName());
            }
            if(!Objects.equals(userQueryRequest.getPhone(), "")) {
                userQuery.setPhone(userQueryRequest.getPhone());
            }
            if(!Objects.equals(userQueryRequest.getEmail(), "")) {
                userQuery.setEmail(userQueryRequest.getEmail());
            }
            if(!Objects.equals(userQueryRequest.getUserRole(), "all")) {
                userQuery.setUserRole(userQueryRequest.getUserRole());
            }

            if(userQueryRequest.getIsBan() != 1000){
                userQuery.setIsBan(userQueryRequest.getIsBan());
            }

            if(userQueryRequest.getGender() != 1000){
                userQuery.setGender(userQueryRequest.getGender());
            }


            current = userQueryRequest.getCurrent();
            size = userQueryRequest.getPageSize();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);

        if(!Objects.equals(userQueryRequest.getCreateTime(), "")){
            String time= userQueryRequest.getCreateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }
        if(!Objects.equals(userQueryRequest.getUpdateTime(), "")){
            String time= userQueryRequest.getUpdateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }

        Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
        Page<UserVO> userVOPage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<UserVO> userVOList = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }

    // endregion

    //region 下级相关

    /**
     * 新增或更新下级用户 以及对应关系表
     * @param userRoleVO userRoleVO
     * @return BaseResponse<String>
     */
    @PostMapping("/emp/saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody UserRoleVO userRoleVO){
        String result = userService.saveOrUpdate(userRoleVO);
        return ResultUtils.success(result);
    }

    /**
     * 删除下级以及关系表数据
     * @param id userid
     * @return BaseResponse<String>
     */
    @DeleteMapping("/emp/delete/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = userService.deleteEmp(id);

        return ResultUtils.success(result);
    }

    @PostMapping("/emp/updateState")
    public BaseResponse<String> updateState(@RequestBody AdminVO adminVO){
        String result = userService.updateState(adminVO);
        return ResultUtils.success(result);
    }

    // endregion


    public static Date transferString2Date(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", s, e);
        }
        return date;
    }
}

