package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.mapper.SysMenuMapper;
import com.hupi.project.service.SysMenuService;
import com.hupi.project.model.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author saoren
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-02-02 18:01:00
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {

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
}




