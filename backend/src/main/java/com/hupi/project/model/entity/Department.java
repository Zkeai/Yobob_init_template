package com.hupi.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 部门表
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String sn;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 状态 0 正常 1 删除
     */
    private Integer status;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建人
     */
    private String create_by;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;



    /**
     * 0 正常 1 删除
     */
    @TableLogic
    private Integer isDelete;
    @TableField(exist = false)
    private List<Department> children = new ArrayList<>();
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}