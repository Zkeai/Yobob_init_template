package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.model.entity.Permission;
import com.hupi.project.service.PermissionService;
import com.hupi.project.mapper.PermissionMapper;
import org.springframework.stereotype.Service;



/**
* @author saoren
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-02-04 15:55:31
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public void load() {


        }
}




