package com.hupi.project.service;

import com.hupi.project.model.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author saoren
* @description 针对表【permission】的数据库操作Service
* @createDate 2023-02-04 15:55:31
*/
public interface PermissionService extends IService<Permission> {

    //加载权限
    void load();

}
