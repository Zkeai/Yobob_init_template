package com.hupi.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hupi.project.model.entity.Department;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.UserRoleVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface UserService extends IService<User> {


    long userRegister(String userAccount, String userPassword, String checkPassword);


    String userLogin(String userAccount, String userPassword, HttpServletRequest request);


    User getLoginUser(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);



    User getSafetyUser(User originUser);


    String saveOrUpdate(UserRoleVO userRoleVO);

    String getUserAuthorityInfo(Long UserId);
}
