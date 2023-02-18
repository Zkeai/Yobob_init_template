package com.hupi.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hupi.project.model.dto.user.UserQueryRequest;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.AdminVO;
import com.hupi.project.model.vo.UserRoleVO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author hupi
 */
@Component
public interface UserService extends IService<User> {


    long userRegister(String userAccount, String userPassword, String checkPassword);



    User getSafetyUser(User originUser);


    String saveOrUpdate(UserRoleVO userRoleVO);

    String deleteEmp(Long id);

    String getUserAuthorityInfo(Long UserId);


    String updateState(AdminVO adminVO);

    User getByAccount(String account);

    PageInfo getList(UserQueryRequest userQueryRequest);

    Boolean updateIsBan(Long isBan,Long id);

}
