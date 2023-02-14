package com.hupi.project.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.dto.department.DepartmentListRequest;
import com.hupi.project.model.entity.Department;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.DepartmentVO;
import com.hupi.project.model.vo.UserVO;
import com.hupi.project.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.JsonPage.restPage;
import static com.hupi.project.util.StringUtils.transferString2Date;

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
        PageHelper.startPage(departmentListRequest.getPageNum(),departmentListRequest.getPageSize());

        Department DepartmentQuery = new Department();
        if (departmentListRequest.getName() !=null && !Objects.equals(departmentListRequest.getName(), "")) {
            DepartmentQuery.setName(departmentListRequest.getName());
        }

        if( departmentListRequest.getStatus() !=null){
            if(departmentListRequest.getStatus() != 1000){
                DepartmentQuery.setStatus(departmentListRequest.getStatus());
            }

        }
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(DepartmentQuery);
        if(!Objects.equals(departmentListRequest.getCreateTime(), "") && departmentListRequest.getCreateTime() !=null){
            String time= departmentListRequest.getCreateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }
        if(!Objects.equals(departmentListRequest.getUpdateTime(), "") && departmentListRequest.getUpdateTime() !=null){
            String time= departmentListRequest.getUpdateTime();
            String a = time.split("`")[0];
            String b = time.split("`")[1];
            queryWrapper.ge("createTime",transferString2Date(a));
            queryWrapper.le("createTime",transferString2Date(b));
        }

        List<Department> list = departmentService.list(queryWrapper);
        PageInfo page = new PageInfo<Department>(list);

        List<DepartmentVO> voList = new ArrayList<>();
        for (Department item:list){
            DepartmentVO departmentVO =assembleDepartmentListVo(item);
            voList.add(departmentVO);
        }
        page.setList(voList);

        return ResultUtils.success(restPage(page));
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


    public static DepartmentVO assembleDepartmentListVo(Department department){

        DepartmentVO departmentVO = new DepartmentVO();
        departmentVO.setId(department.getId());
        departmentVO.setAncestors(department.getAncestors());
        departmentVO.setSn(department.getSn());
        departmentVO.setEmail(department.getEmail());
        departmentVO.setName(department.getName());
        departmentVO.setLeader(department.getLeader());
        departmentVO.setCreate_by(department.getCreate_by());
        departmentVO.setParentId(department.getParentId());
        departmentVO.setStatus(department.getStatus());
        departmentVO.setUpdateBy(department.getUpdateBy());
        departmentVO.setPhone(department.getPhone());
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime_ = sdf3.format(department.getCreateTime());
        String updateTime_ = sdf3.format(department.getUpdateTime());
        departmentVO.setCreateTime(createTime_);
        departmentVO.setUpdateTime(updateTime_);
        return departmentVO;
    }

}
