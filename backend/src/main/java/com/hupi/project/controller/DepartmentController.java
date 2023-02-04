package com.hupi.project.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.annotation.RequiredPermission;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.entity.Department;
import com.hupi.project.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
     * @param pageNum 页面
     * @param pageSize 页面数量
     * @return pageInfo
     */
    @GetMapping("/list/page")
    @RequiredPermission(name = "部门列表",expression = "department:list")
    public BaseResponse<Object> listUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Department> list = departmentService.list();
        PageInfo<Department> page = new PageInfo<Department>(list);

        return ResultUtils.success(restPage(page));
    }

    /**
     * 删除部门
     * @param id 部门id
     * @return success/error
     */
    @DeleteMapping("/delete/{id}")
    @RequiredPermission(name = "部门删除",expression = "department:delete")
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
    @RequiredPermission(name = "部门新增或编辑",expression = "department:saveOrUpdate")
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
    @RequiredPermission(name = "部门单个信息",expression = "department:info")
    public BaseResponse<Department> getInfo (@PathVariable Long id){
        Department result = departmentService.getInfo(id);
        return ResultUtils.success(result);
    }

    /**
     * 获取所有部门信息
     * @return List<Department>
     */
    @GetMapping("/listAll")
    @RequiredPermission(name = "所有部门信息",expression = "department:listAll")
    public BaseResponse<List<Department>> getListAll(){
        List<Department> departments = departmentService.list();
        return ResultUtils.success(departments);
    }
}
