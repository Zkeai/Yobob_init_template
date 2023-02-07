package com.hupi.project.common.security;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.entity.SysMenu;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.entity.User;
import com.hupi.project.service.SysMenuService;
import com.hupi.project.service.SysRoleService;
import com.hupi.project.service.UserService;
import com.hupi.project.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private UserService userService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysMenuService sysMenuService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();


        Map<String,Object> data = new ConcurrentHashMap<>();
        String account = authentication.getName();
        String token = JwtUtil.createToken(account);

        User currentUser = userService.getByAccount(account);
        currentUser =userService.getSafetyUser(currentUser);


        //根据用户id获取所有的角色信息
        List<SysRole> roleList  = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id","SELECT role_id FROM sys_user_role WHERE user_id="+currentUser.getId()));

        //遍历所有的角色,获取所有的菜单权限且不重复
        Set<SysMenu> menuSet = new HashSet<>();
        for(SysRole sysRole:roleList){
            List<SysMenu> sysMenuList =  sysMenuService.list(new QueryWrapper<SysMenu>().inSql("id","SELECT menu_id FROM sys_role_menu WHERE role_id="+sysRole.getId()));
            for(SysMenu sysMenu:sysMenuList){
                menuSet.add(sysMenu);
            }
        }

        List<SysMenu> sysMenuList = new ArrayList<>(menuSet);

        //排序
        sysMenuList.sort(Comparator.comparing(SysMenu::getOrder_num));

        //转菜单数
        List<SysMenu> menuList = sysMenuService.buildTreeMenu(sysMenuList);

        data.put("token",token);
        data.put("currentUser",currentUser);
        data.put("menuList",menuList);
        outputStream.write(JSONUtil.toJsonStr( ResultUtils.success(data)).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
