package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.SysRoleMapper;
import com.hupi.project.model.dto.role.RoleListRequest;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.vo.RoleDeleteVO;
import com.hupi.project.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.StringUtils.transferString2Date;

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
    private SysRoleService sysRoleService;

    @Override
    public String saveOrUpdate(RoleDeleteVO roleDeleteVO) {
        if(roleDeleteVO ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(roleDeleteVO.getSysRole() ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        SysRole role = roleDeleteVO.getSysRole();

        if(roleDeleteVO.getSysRole().getId() == null || roleDeleteVO.getSysRole().getId() == 0){
            //调用新增方法 把下级信息保存到role数据库
            sysRoleMapper.insert(role);
        }else{
            //编辑方法
            sysRoleMapper.updateById(role);
            //删除中间表 角色-部门
            sysRoleMapper.deleteRelationDept(role.getId());
            //删除中间表 角色-菜单
            sysRoleMapper.deleteRelationMenu(role.getId());
        }

        //插入新关系 角色-部门
        Long[] deptIds = roleDeleteVO.getDeptIds();
        if(deptIds !=null && deptIds.length > 0){
            sysRoleMapper.insertBatchRelationDept(role.getId(),deptIds);
        }
        //插入新关系 角色-菜单
        Long[] menuIds = roleDeleteVO.getMenuIds();
        if(deptIds !=null && deptIds.length > 0){
            sysRoleMapper.insertBatchRelationMenu(role.getId(),menuIds);
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
        //删除中间表 角色-部门
        sysRoleMapper.deleteRelationDept(id);
        //删除中间表 角色-菜单
        sysRoleMapper.deleteRelationMenu(id);
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

    @Override
    public PageInfo getList(RoleListRequest roleListRequest) {
        PageHelper.startPage(roleListRequest.getPageNum(),roleListRequest.getPageSize());

        String name = roleListRequest.getName();
        Integer isBan = roleListRequest.getIsBan();
        String createTime =roleListRequest.getCreateTime();
        String updateTime =roleListRequest.getUpdateTime();
        String code = roleListRequest.getCode();
        SysRole SysRoleQuery = new SysRole();
        if ( name!=null && !Objects.equals(name, "")) {
            SysRoleQuery.setName(roleListRequest.getName());
        }
        if(code!=null && !Objects.equals(code, "")){
            SysRoleQuery.setCode(SysRoleQuery.getCode());
        }
        if( isBan !=null){
            if(isBan != 1000){
                SysRoleQuery.setIsBan(isBan);
            }
        }

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>(SysRoleQuery);
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


        List<SysRole> list = sysRoleService.list();
        PageInfo<SysRole> page = new PageInfo<SysRole>(list);
        page.setList(list);
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

        int res = sysRoleMapper.updateIsBanById(isBan,id);
        if(res>0){
            return true;
        }
        return false;
    }
}




