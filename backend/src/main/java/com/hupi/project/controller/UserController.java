package com.hupi.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.JsonPage.restPage;

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
     * @return List<UserVO>
     */
    @PostMapping("/list")
    public BaseResponse<Object> listUser(@RequestBody UserQueryRequest userQueryRequest) {
        PageHelper.startPage(userQueryRequest.getPageNum(),userQueryRequest.getPageSize());

        User userQuery = new User();
        if (userQueryRequest.getUserName() !=null && !Objects.equals(userQueryRequest.getUserName(), "")) {
            userQuery.setUserName(userQueryRequest.getUserName());
        }
        if(!Objects.equals(userQueryRequest.getPhone(), "") && userQueryRequest.getPhone() !=null) {
            userQuery.setPhone(userQueryRequest.getPhone());
        }
        if(!Objects.equals(userQueryRequest.getEmail(), "") && userQueryRequest.getEmail() !=null) {
            userQuery.setEmail(userQueryRequest.getEmail());
        }

        if(userQueryRequest.getIsBan() !=null){
            if(userQueryRequest.getIsBan() != 1000){
                userQuery.setIsBan(userQueryRequest.getIsBan());
            }

        }

        if( userQueryRequest.getGender() !=null){
            if(userQueryRequest.getGender() != 1000){
                userQuery.setGender(userQueryRequest.getGender());
            }

        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);

        if(!Objects.equals(userQueryRequest.getCreateTime(), "") && userQueryRequest.getCreateTime() !=null){
            String time= userQueryRequest.getCreateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }
        if(!Objects.equals(userQueryRequest.getUpdateTime(), "") && userQueryRequest.getUpdateTime() !=null){
            String time= userQueryRequest.getUpdateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }

        List<User> userList = userService.list(queryWrapper);

        PageInfo pageResult = new PageInfo<>(userList);

        List<UserVO> voList = new ArrayList<>();
        for (User item:userList){
            UserVO userVO =assembleUserListVo(item);
            voList.add(userVO);
        }

        pageResult.setList(voList);

        PageInfo<User> page = new PageInfo<User>(userList);


        return ResultUtils.success(restPage(pageResult));
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

    /**
     * 修改用户超级管理员权限
     * @param adminVO adminVO
     * @return String
     */
    @PostMapping("/emp/updateState")
    public BaseResponse<String> updateState(@RequestBody AdminVO adminVO){
        String result = userService.updateState(adminVO);
        return ResultUtils.success(result);
    }

    // endregion
public static UserVO assembleUserListVo(User user){

    UserVO userVO = new UserVO();
    userVO.setId(user.getId());
    userVO.setUserName(user.getUserName());
    userVO.setGender(user.getGender());
    userVO.setEmail(user.getEmail());
    userVO.setPhone(user.getPhone());
    userVO.setUserAvatar(user.getUserAvatar());
    userVO.setUserAccount(user.getUserAccount());
    userVO.setAge(user.getAge());
    userVO.setDeptId(user.getDeptId());
    userVO.setStatus(user.getStatus());
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createTime_ = sdf3.format(user.getCreateTime());
    String updateTime_ = sdf3.format(user.getUpdateTime());
    userVO.setCreateTime(createTime_);
    userVO.setUpdateTime(updateTime_);
    userVO.setIsBan(user.getIsBan());
    return userVO;
}

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

