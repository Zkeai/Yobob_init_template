package com.hupi.project.model.dto.user;

import com.hupi.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询请求
 *
 * @author zkeai
 */
@Data
public class UserQueryRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 性别
     */
    private Integer gender;


    /**
     * 页数
     */
    private int pageNum;

    /**
     * 每一页数量
     */
    private int pageSize;
    /**
     * 封禁
     */
    private Integer isBan;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    private static final long serialVersionUID = 1L;


}