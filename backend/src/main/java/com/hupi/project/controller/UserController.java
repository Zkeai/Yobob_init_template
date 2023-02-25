package com.hupi.project.controller;

import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.SysUserRoleMapper;
import com.hupi.project.model.dto.other.IsBanRequest;
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
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


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

    @PreAuthorize("hasAnyAuthority('system:user:query')")
    public BaseResponse<Object> listUser(@RequestBody UserQueryRequest userQueryRequest) {

        PageInfo list= userService.getList( userQueryRequest);

        return ResultUtils.success(restPage(list));
    }


    /**
     * 新增或更新下级用户 以及对应关系表
     * @param userRoleVO userRoleVO
     * @return BaseResponse<String>
     */
    @PostMapping("/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('system:user:add','system:user:edit')")
    public BaseResponse<String> saveOrUpdate(@RequestBody UserRoleVO userRoleVO){
        String result = userService.saveOrUpdate(userRoleVO);
        return ResultUtils.success(result);
    }

    /**
     * 删除下级以及关系表数据
     * @param id userid
     * @return BaseResponse<String>
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('system:user:delete')")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = userService.deleteEmp(id);

        return ResultUtils.success(result);
    }

    /**
     * 修改用户超级管理员权限
     * @param adminVO adminVO
     * @return String
     */
    @PostMapping("/updateState")
    @PreAuthorize("hasRole('ROLE_admin')")
    public BaseResponse<String> updateState(@RequestBody AdminVO adminVO){
        String result = userService.updateState(adminVO);
        return ResultUtils.success(result);
    }

    @PostMapping("/updateIsBan")
    @PreAuthorize("hasRole('ROLE_admin')")
    public BaseResponse<Boolean> updateIsBan(@RequestBody IsBanRequest isBanRequest){
        Long isBan = isBanRequest.getIsBan();
        Long id = isBanRequest.getId();
        Boolean res = userService.updateIsBan(isBan,id);
        return ResultUtils.success(res);
    }
    // endregion
public static UserVO assembleUserListVo(UserRoleVO user){

    UserVO userVO = new UserVO();
    userVO.setId(user.getUser().getId());
    userVO.setUserName(user.getUser().getUserName());
    userVO.setGender(user.getUser().getGender());
    userVO.setEmail(user.getUser().getEmail());
    userVO.setPhone(user.getUser().getPhone());
    userVO.setUserAvatar(user.getUser().getUserAvatar());
    userVO.setUserAccount(user.getUser().getUserAccount());
    userVO.setAge(user.getUser().getAge());
    userVO.setDeptId(user.getUser().getDeptId());
    userVO.setStatus(user.getUser().getStatus());
    userVO.setCreateTime(user.getUser().getCreateTime());
    userVO.setUpdateTime(user.getUser().getUpdateTime());
    userVO.setIsBan(user.getUser().getIsBan());
    userVO.setRoleIds(user.getRoleIds());
    userVO.setPostIds(user.getPostIds());
    return userVO;
}


}

