package com.hupi.project.common.security;

import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.entity.SysMenu;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import com.hupi.project.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义UserDetails
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        com.hupi.project.model.entity.User user=userService.getByAccount(userAccount);
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名不存在!!");
        }else if(user.getIsBan() == 1){
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR,"该用户账号已被封禁，具体联系管理员!");
        }
        return new User(user.getUserAccount(),user.getUserPassword(),getUserAuthority(user.getId()));
    }

    /**
     * 获取用户权限信息 包括角色 菜单权限信息
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Long userId){
        // 格式ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,system:user:list,system:menu:query,system:menu:list,system:menu:add,system:user:delete,system:role:list,system:role:menu,system:user:edit,system:user:query,system:role:edit,system:user:add,system:user:role,system:menu:delete,system:role:add,system:role:query,system:menu:edit
        String authority=userService.getUserAuthorityInfo(userId);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
