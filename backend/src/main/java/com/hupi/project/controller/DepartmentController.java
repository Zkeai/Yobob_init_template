package com.hupi.project.controller;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.dto.department.DepartmentListRequest;
import com.hupi.project.model.entity.Department;

import com.hupi.project.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;

import java.util.List;


import static com.hupi.project.util.JsonPage.restPage;


/**
 * 部门控制层
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;


    /**
     * 根据pageNum 和 pageSize 获取部门列表
     * @return pageInfo
     */
    @PostMapping("/list")
    //@PreAuthorize("hasRole('ROLE_admin')")
    //@PreAuthorize("hasAnyAuthority('system:menu:add1')")
    public BaseResponse<Object> listUserByPage(@RequestBody DepartmentListRequest departmentListRequest) {

        PageInfo list = departmentService.getList( departmentListRequest);


        return ResultUtils.success(restPage(list));
    }

    /**
     * 删除部门
     * @param id 部门id
     * @return success/error
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = departmentService.deleteDep(id);

        return ResultUtils.success(result);
    }

    /**
     * 新增或更新部门
     * @param department department
     * @return success/error
     */
    @PostMapping("/saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody Department department){
       String result = departmentService.saveOrUpdateDep(department);
        return ResultUtils.success(result);
    }

    /**
     * 获取单个部门信息
     * @param id id
     * @return Department
     */
    @GetMapping("/info/{id}")
    public BaseResponse<Department> getInfo (@PathVariable Long id){
        Department result = departmentService.getInfo(id);
        return ResultUtils.success(result);
    }

    /**
     * 获取所有部门信息
     * @return List<Department>
     */
    @GetMapping("/listAll")
    public BaseResponse<List<Department>> getListAll(){
        List<Department> depts = departmentService.list();

        return ResultUtils.success(depts);
    }


}
