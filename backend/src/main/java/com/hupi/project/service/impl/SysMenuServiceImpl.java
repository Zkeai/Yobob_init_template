package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.mapper.SysMenuMapper;
import com.hupi.project.model.dto.menu.MenuListRequest;
import com.hupi.project.model.entity.Department;
import com.hupi.project.service.SysMenuService;
import com.hupi.project.model.entity.SysMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.StringUtils.transferString2Date;

/**
* @author saoren
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-02-02 18:01:00
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {
    @Resource
    private SysMenuService sysMenuService;
    @Override
    public List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList) {
        List<SysMenu> resultMenulist = new ArrayList<>();

        for(SysMenu sysMenu:sysMenuList){
            //寻找子节点
            for(SysMenu e:sysMenuList){
                if(Objects.equals(e.getParent_id(), sysMenu.getId())){
                    sysMenu.getChildren().add(e);
                }
            }


            if(sysMenu.getParent_id() ==0L){
                resultMenulist.add(sysMenu);
            }
        }


        return resultMenulist;
    }

    @Override
    public PageInfo getList(MenuListRequest menuListRequest) {
        PageHelper.startPage(menuListRequest.getPageNum(),menuListRequest.getPageSize());
        String name = menuListRequest.getName();
        Integer isBan = menuListRequest.getIsBan();
        String createTime =menuListRequest.getCreateTime();
        String updateTime =menuListRequest.getUpdateTime();
        SysMenu SysMenuQuery = new SysMenu();
        if ( name!=null && !Objects.equals(name, "")) {
            SysMenuQuery.setName(menuListRequest.getName());
        }

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>(SysMenuQuery);
        if(!Objects.equals(createTime, "") && createTime != null){
            Date a = transferString2Date(createTime.split("`")[0]);
            Date b = transferString2Date(createTime.split("`")[1]);
            queryWrapper.ge("createTime",a);
            queryWrapper.le("createTime",b);
        }
        if(!Objects.equals(updateTime, "") && updateTime != null){
            Date a = transferString2Date(updateTime.split("`")[0]);
            Date b = transferString2Date(updateTime.split("`")[1]);
            queryWrapper.ge("updateTime",a);
            queryWrapper.le("updateTime",b);
        }
        List<SysMenu> sysMenuList = sysMenuService.list(queryWrapper);

        List<SysMenu> resultMenulist = new ArrayList<>();
        if(name == null && isBan== null && createTime== null && updateTime==null){
            for(SysMenu sysMenu:sysMenuList){
                //寻找子节点
                for(SysMenu e:sysMenuList){
                    if(Objects.equals(e.getParent_id(), sysMenu.getId())){
                        sysMenu.getChildren().add(e);
                    }
                }
                if(sysMenu.getParent_id() ==0L){
                    resultMenulist.add(sysMenu);
                }
            }
            PageInfo<SysMenu> page = new PageInfo<SysMenu>(resultMenulist);

            page.setList(resultMenulist);


            return page;
        }
        PageInfo<SysMenu> page = new PageInfo<SysMenu>(sysMenuList);

        page.setList(sysMenuList);

        return page;

    }
}




