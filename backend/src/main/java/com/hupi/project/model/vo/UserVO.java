package com.hupi.project.model.vo;

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

    /**
     * 部门id
     */
    private Integer deptId;
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
     * 用户角色: user, admin
     */
    private String userRole;


    /**
     * 是不是超级管理员
     */
    private Integer status;
    private Integer isBan;

    private String createTime;

    private String updateTime;
    private static final long serialVersionUID = 1L;
}