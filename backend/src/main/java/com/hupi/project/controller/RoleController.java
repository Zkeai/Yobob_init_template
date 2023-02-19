package com.hupi.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.dto.other.IsBanRequest;
import com.hupi.project.model.dto.role.RoleListRequest;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.vo.RoleDeleteVO;
import com.hupi.project.model.vo.RolePermissionVO;
import com.hupi.project.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.hupi.project.util.JsonPage.restPage;

/**
 * 角色 控制层
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private SysRoleService sysRoleService;


    /**
     * 根据pageNum 和 pageSize 获取角色列表
     * roleListRequest roleListRequest
     * @return pageInfo
     */
    @PostMapping("/list")
    public BaseResponse<Object> listRoleByPage(@RequestBody RoleListRequest roleListRequest) {
        PageInfo list = sysRoleService.getList(roleListRequest);

        return ResultUtils.success(restPage(list));
    }

    /**
     * 删除角色 删除关系表 角色-部门（已完成） 角色-menu（已完成）
     * @param id 角色id
     * @return success/error
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = sysRoleService.deleteDep(id);

        return ResultUtils.success(result);
    }

    /**
     * 新增或更新角色 角色-部门（已完成） 角色-menu（待添加）
     * @param roleDeleteVO rolePermissionVO
     * @return success/error
     */
    @PostMapping("/saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody RoleDeleteVO roleDeleteVO){
       String result = sysRoleService.saveOrUpdate(roleDeleteVO);
        return ResultUtils.success(result);
    }

    /**
     * 获取单个角色信息
     * @param id id
     * @return SysRole
     */
    @GetMapping("/info/{id}")
    public BaseResponse<SysRole> getInfo (@PathVariable Long id){
        SysRole result = sysRoleService.getInfo(id);
        return ResultUtils.success(result);
    }

    /**
     * 获取所有角色信息
     * @return List<SysRole>
     */
    @GetMapping("/listAll")
    public BaseResponse<List<SysRole>> getListAll(){
        List<SysRole> SysRoles = sysRoleService.list();
        return ResultUtils.success(SysRoles);
    }

    /**
     * 查询拥有的角色
     * @param id id
     * @return BaseResponse<List<SysRole>>
     */
    @GetMapping("/query/{id}")
    public BaseResponse<List<SysRole>> query(@PathVariable Long id){
        List<SysRole> rs = sysRoleService.queryByUid(id);
        return ResultUtils.success(rs);
    }


    @PostMapping("/updateIsBan")
    public BaseResponse<Boolean> updateIsBan(@RequestBody IsBanRequest isBanRequest){
        Long isBan = isBanRequest.getIsBan();
        Long id = isBanRequest.getId();
        Boolean res = sysRoleService.updateIsBan(isBan,id);
        return ResultUtils.success(res);
    }
}
