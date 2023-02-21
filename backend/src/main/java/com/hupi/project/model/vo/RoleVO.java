package com.hupi.project.model.vo;

import com.hupi.project.model.entity.Department;
import com.hupi.project.model.entity.SysMenu;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleVO {
    private SysRole sysRole;
    private List<SysMenu> menuIList;
}
