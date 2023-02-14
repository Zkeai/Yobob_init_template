package com.hupi.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_user_post
 */
@TableName(value ="sys_user_post")
@Data
public class SysUserPost implements Serializable {
    /**
     * 
     */
    private Long user_id;

    /**
     * 
     */
    private Long post_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}