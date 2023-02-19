package com.hupi.project.service;

import com.github.pagehelper.PageInfo;
import com.hupi.project.model.dto.role.RoleListRequest;
import com.hupi.project.model.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hupi.project.model.vo.RoleDeleteVO;

import java.util.List;

/**
* @author saoren
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-02-02 18:01:00
*/
public interface SysRoleService extends IService<SysRole> {
    String saveOrUpdate(RoleDeleteVO roleDeleteVO);

    String deleteDep(Long id);

    SysRole getInfo(Long id);

    List<SysRole> queryByUid(Long id);

    PageInfo getList(RoleListRequest roleListRequest);

    Boolean updateIsBan(Long isBan, Long id);
}
