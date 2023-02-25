package com.hupi.project.controller;

import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.dto.menu.MenuListRequest;
import com.hupi.project.model.dto.post.PostListRequest;
import com.hupi.project.model.entity.Post;
import com.hupi.project.model.entity.SysMenu;
import com.hupi.project.model.vo.UserRoleVO;
import com.hupi.project.service.PostService;
import com.hupi.project.service.SysMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.JsonPage.restPage;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('system:menu:list')")
    public BaseResponse<Object> listMenuByPage(@RequestBody MenuListRequest menuListRequest) {
        PageInfo list = sysMenuService.getList(menuListRequest);

        return ResultUtils.success(restPage(list));
    }
    @GetMapping("/listAll")
    public BaseResponse<List<SysMenu>> getAll() {
        List<SysMenu> sysMenuList = sysMenuService.list();

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

        return ResultUtils.success(resultMenulist);
    }

    @PostMapping("/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('system:menu:edit','system:menu:add')")
    public BaseResponse<String> saveOrUpdate(@RequestBody SysMenu sysMenu){
        String result = sysMenuService.saveOrUpdater(sysMenu);
        return ResultUtils.success(result);
    }
}
