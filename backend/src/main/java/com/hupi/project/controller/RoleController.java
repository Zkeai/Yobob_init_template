package com.hupi.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.annotation.RequiredPermission;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.entity.SysRole;
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
     * @param pageNum 页面
     * @param pageSize 页面数量
     * @return pageInfo
     */
    @GetMapping("/list/page")
    @RequiredPermission(name = "角色分页列表",expression = "role:list")
    public BaseResponse<Object> listRoleByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> list = sysRoleService.list();
        PageInfo<SysRole> page = new PageInfo<SysRole>(list);

        return ResultUtils.success(restPage(page));
    }

    /**
     * 删除角色 删除 角色-权限（已完成） 角色-menu（待添加）
     * @param id 角色id
     * @return success/error
     */
    @DeleteMapping("/delete/{id}")
    @RequiredPermission(name = "角色删除",expression = "role:delete")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = sysRoleService.deleteDep(id);

        return ResultUtils.success(result);
    }

    /**
     * 新增或更新角色 角色-权限（已完成） 角色-menu（待添加）
     * @param rolePermissionVO rolePermissionVO
     * @return success/error
     */
    @PostMapping("/saveOrUpdate")
    @RequiredPermission(name = "角色新增或修改",expression = "role:saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody RolePermissionVO rolePermissionVO){
       String result = sysRoleService.saveOrUpdatePer(rolePermissionVO);
        return ResultUtils.success(result);
    }

    /**
     * 获取单个角色信息
     * @param id id
     * @return SysRole
     */
    @GetMapping("/info/{id}")
    @RequiredPermission(name = "角色单个信息",expression = "role:info")
    public BaseResponse<SysRole> getInfo (@PathVariable Long id){
        SysRole result = sysRoleService.getInfo(id);
        return ResultUtils.success(result);
    }

    /**
     * 获取所有角色信息
     * @return List<SysRole>
     */
    @GetMapping("/listAll")
    @RequiredPermission(name = "所有角色信息",expression = "role:listAll")
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
    @RequiredPermission(name = "查询拥有的角色",expression = "role:query")
    public BaseResponse<List<SysRole>> query(@PathVariable Long id){
        List<SysRole> rs = sysRoleService.queryByUid(id);
        return ResultUtils.success(rs);
    }
}
