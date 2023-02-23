package com.hupi.project.service;

import com.github.pagehelper.PageInfo;
import com.hupi.project.model.dto.menu.MenuListRequest;
import com.hupi.project.model.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hupi.project.model.vo.UserRoleVO;

import java.util.List;

/**
* @author saoren
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-02-02 18:01:00
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList);

    PageInfo getList(MenuListRequest menuListRequest);

    String saveOrUpdater(SysMenu sysMenu);

}
