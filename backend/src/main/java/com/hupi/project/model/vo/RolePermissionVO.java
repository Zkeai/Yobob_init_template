package com.hupi.project.model.vo;

import com.hupi.project.model.entity.SysRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionVO {
    private SysRole sysRole;
    private Long[] permissionIds;

}
