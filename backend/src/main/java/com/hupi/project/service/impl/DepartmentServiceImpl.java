package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.entity.Department;
import com.hupi.project.service.DepartmentService;
import com.hupi.project.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author saoren
* @description 针对表【department(部门表)】的数据库操作Service实现
* @createDate 2023-02-02 23:33:15
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public String saveOrUpdateDep(Department departments) {
        if(departments == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法操作");
        }

        String name =departments.getName();
        String sn = departments.getSn();
        Long parentId =departments.getParentId();
        String ancestors =departments.getAncestors();
        Integer status = departments.getStatus();
        String leader = departments.getLeader();
        String phone =departments.getPhone();
        String email=departments.getEmail();
        String updateBy =departments.getUpdateBy();

        QueryWrapper<Department> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", name);
        Department department1= departmentMapper.selectOne(queryWrapper1);
        if(department1 == null){
            this.save(departments);
        }else{
            Long id = department1.getId();
            Timestamp time2 = new Timestamp(new Date().getTime());
            Department newDepartment =new Department();
            newDepartment.setUpdateTime(time2);
            newDepartment.setAncestors(ancestors);
            newDepartment.setStatus(status);
            newDepartment.setEmail(email);
            newDepartment.setParentId(parentId);
            newDepartment.setName(name);
            newDepartment.setLeader(leader);
            newDepartment.setSn(sn);
            newDepartment.setPhone(phone);
            newDepartment.setUpdateBy(updateBy);
            newDepartment.setId(id);
            departmentMapper.updateById(newDepartment);


        }
        return "success";
    }

    @Override
    public String deleteDep(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int count = departmentMapper.deleteById(id);
        if(count == 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return "success";
    }

    @Override
    public Department getInfo(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
            Department department = departmentMapper.selectById(id);
        if(department == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        return department;
    }



}




