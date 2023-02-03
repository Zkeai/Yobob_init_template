package com.hupi.project.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.common.ResultUtils;

import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.DepartmentMapper;
import com.hupi.project.model.entity.Department;


import com.hupi.project.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.hupi.project.util.JsonPage.restPage;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 根据pageNum 和 pageSize 获取部门列表
     * @param pageNum 页面
     * @param pageSize 页面数量
     * @return pageInfo
     */
    @GetMapping("/list/page")
    public BaseResponse<Object> listUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Department> list = departmentService.list();
        PageInfo<Department> page = new PageInfo<Department>(list);

        return ResultUtils.success(restPage(page));
    }

    /**
     * 删除部门
     * @param id 部门id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int count = departmentMapper.deleteById(id);
        if(count == 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("success");
    }


    @PostMapping("/saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody Department department){
       String result = departmentService.saveOrUpdateDep(department);
        return ResultUtils.success(result);
    }
}
