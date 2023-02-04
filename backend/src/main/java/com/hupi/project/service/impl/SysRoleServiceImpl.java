package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.SysRoleMapper;
import com.hupi.project.mapper.SysRolePermissionMapper;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.RolePermissionVO;
import com.hupi.project.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author saoren
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-02-02 18:01:00
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public String saveOrUpdatePer(RolePermissionVO rolePermissionVO) {
        if(rolePermissionVO ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(rolePermissionVO.getSysRole() ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        SysRole role = rolePermissionVO.getSysRole();

        if(rolePermissionVO.getSysRole().getId() == null || rolePermissionVO.getSysRole().getId() == 0){
            //调用新增方法 把下级信息保存到role数据库
            sysRoleMapper.insert(role);
        }else{
            //编辑方法
            sysRoleMapper.updateById(role);
            //删除旧关系
            Map<String,Object> map=new HashMap<>();
            map.put("role_id",role.getId());
            sysRolePermissionMapper.deleteByMap(map);
        }

        //插入新关系
        Long[] permissionIds = rolePermissionVO.getPermissionIds();
        if(permissionIds !=null && permissionIds.length > 0){
            //批量插入
            sysRoleMapper.insertBatchRelation(role.getId(),permissionIds);
        }
        return null;
    }

    @Override
    public String deleteDep(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int count = sysRoleMapper.deleteById(id);
        if(count == 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        //删除中间表信息
        sysRoleMapper.deleteRelation(id);
        return "success";
    }

    @Override
    public SysRole getInfo(Long id) {

        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SysRole sysRole = sysRoleMapper.selectById(id);
        if(sysRole == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        return sysRole;
    }

    @Override
    public List<SysRole> queryByUid(Long id) {
        if(id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return sysRoleMapper.queryById(id);
    }
}




