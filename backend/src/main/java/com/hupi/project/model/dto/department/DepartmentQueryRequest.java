package com.hupi.project.model.dto.department;

import com.hupi.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门查询请求
 *
 * @author hupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     *sn
     */
    private String sn;



    private static final long serialVersionUID = 1L;
}