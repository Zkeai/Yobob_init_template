package com.hupi.project.mapper;

import com.hupi.project.model.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author saoren
* @description 针对表【department(部门表)】的数据库操作Mapper
* @createDate 2023-02-02 23:33:15
* @Entity com.hupi.project.model.entity.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {

    int updateIsBanById(@Param("isBan") Long isBan, @Param("id") Long id);
}




