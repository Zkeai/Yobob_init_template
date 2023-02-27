package com.hupi.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图
 *
 * @TableName user
 */
@Data
public class UserVO implements Serializable {
    /**
     * 用户昵称
     */
    private String userName;

    private Long id;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 是不是超级管理员
     */
    private Integer status;
    private Integer isBan;

    private Date createTime;

    private Date updateTime;


    @TableField(exist = false)
    private Long[] postIds;

    @TableField(exist = false)
    private Long[] roleIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}