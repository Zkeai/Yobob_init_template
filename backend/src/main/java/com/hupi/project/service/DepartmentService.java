package com.hupi.project.service;

import com.hupi.project.model.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author saoren
* @description 针对表【department(部门表)】的数据库操作Service
* @createDate 2023-02-02 23:33:15
*/
public interface DepartmentService extends IService<Department> {

    String saveOrUpdateDep(Department department);

}
