package com.hupi.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class SysRoleMenu implements Serializable {

    /**
     * 角色ID
     */
    private Long role_id;

    /**
     * 菜单ID
     */
    private Long menu_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}