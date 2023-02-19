package com.hupi.project.model.dto.post;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostSaveOrUpdateRequest implements Serializable {
    private Long id;
    private String name;

    private String code;

    private Integer isBan;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
