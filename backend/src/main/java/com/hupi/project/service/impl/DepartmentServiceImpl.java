package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.dto.department.DepartmentListRequest;
import com.hupi.project.model.entity.Department;
import com.hupi.project.service.DepartmentService;
import com.hupi.project.mapper.DepartmentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

import static com.hupi.project.util.StringUtils.transferString2Date;

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
    @Resource
    private DepartmentService departmentService;
    @Override
    public String saveOrUpdateDep(Department departments) {
        if(departments == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法操作");
        }



        if(departments.getId() == null){
            Timestamp time2 = new Timestamp(new Date().getTime());
            departments.setCreateTime(time2);
            this.save(departments);
        }else{
            String name =departments.getName();
            String sn = departments.getSn();
            Long parentId =departments.getParentId();
            String ancestors =departments.getAncestors();
            Integer isBan = departments.getIsBan();
            String leader = departments.getLeader();
            String phone =departments.getPhone();
            String email=departments.getEmail();
            String updateBy =departments.getUpdateBy();

            Timestamp time2 = new Timestamp(new Date().getTime());
            Department newDepartment =new Department();
            newDepartment.setId(departments.getId());
            newDepartment.setUpdateTime(time2);
            newDepartment.setAncestors(ancestors);
            newDepartment.setIsBan(isBan);
            newDepartment.setEmail(email);
            newDepartment.setParentId(parentId);
            newDepartment.setName(name);
            newDepartment.setLeader(leader);
            newDepartment.setSn(sn);
            newDepartment.setPhone(phone);
            newDepartment.setUpdateBy(updateBy);
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

    @Override
    public PageInfo getList(DepartmentListRequest departmentListRequest) {

        PageHelper.startPage(departmentListRequest.getPageNum(),departmentListRequest.getPageSize());
        String name = departmentListRequest.getName();
        Integer isBan = departmentListRequest.getIsBan();
        String createTime =departmentListRequest.getCreateTime();
        String updateTime =departmentListRequest.getUpdateTime();
        Department DepartmentQuery = new Department();
        if ( name!=null && !Objects.equals(name, "")) {
            DepartmentQuery.setName(departmentListRequest.getName());
        }
        if( isBan !=null){
            if(isBan != 1000){
                DepartmentQuery.setIsBan(isBan);
            }
        }

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(DepartmentQuery);
        if(!Objects.equals(createTime, "") && createTime != null){
            Date a = transferString2Date(createTime.split("`")[0]);
            Date b = transferString2Date(createTime.split("`")[1]);
            queryWrapper.ge("createTime",a);
            queryWrapper.le("createTime",b);
        }
        if(!Objects.equals(updateTime, "") && updateTime != null){
            Date a = transferString2Date(updateTime.split("`")[0]);
            Date b = transferString2Date(updateTime.split("`")[1]);
            queryWrapper.ge("updateTime",a);
            queryWrapper.le("updateTime",b);
        }

        List<Department> departmentsList = departmentService.list(queryWrapper);
        List<Department> resultMenulist = new ArrayList<>();
        if(name == null && isBan== null && createTime== null && updateTime==null){
            for(Department department:departmentsList){
                //寻找子节点
                for(Department e:departmentsList){
                    if(Objects.equals(e.getParentId(), department.getId())){
                        department.getChildren().add(e);
                    }
                }
                if(department.getParentId() ==0L){
                    resultMenulist.add(department);
                }
            }
            PageInfo<Department> page = new PageInfo<Department>(resultMenulist);

            page.setList(resultMenulist);
            return page;
        }


        PageInfo<Department> page = new PageInfo<Department>(departmentsList);

        page.setList(departmentsList);
        return page;



    }

    @Override
    public Boolean updateIsBan(Long isBan, Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(isBan !=0 && isBan !=1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        int res = departmentMapper.updateIsBanById(isBan,id);
        if(res>0){
            return true;
        }
        return false;
    }


}




