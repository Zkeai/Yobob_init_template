package com.hupi.project.mapper;

import com.hupi.project.model.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author saoren
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2023-02-02 18:01:00
* @Entity com.hupi.project.model.entity.generator.domain.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    void deleteRelation(@Param("id") Long id);


    List<SysRole> queryById(@Param("id") Long id);

    void insertBatchRelation(@Param("roleId") Long roleId,@Param("permissionIds") Long[] permissionIds);

}




