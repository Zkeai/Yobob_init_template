package com.hupi.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * id
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer isBan;

    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新时间
     */
    private Date update_time;
    /**
     * 岗位标识
     */
    private String code;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}