package com.hupi.project.model.dto.role;

import com.hupi.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色查询请求
 *
 * @author hupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleListRequest extends PageRequest implements Serializable {

    /**
     * 页数
     */
    private int pageNum;
    /**
     * 每一页数量
     */
    private int pageSize;
    private String name;
    private Integer isBan;
    private String code;
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