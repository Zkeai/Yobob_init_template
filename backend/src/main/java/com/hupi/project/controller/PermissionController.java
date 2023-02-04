package com.hupi.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;
import com.hupi.project.model.entity.Permission;
import com.hupi.project.model.vo.RolePermissionVO;
import com.hupi.project.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.hupi.project.util.JsonPage.restPage;

/**
 * 权限 控制层
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;


    /**
     * 根据pageNum 和 pageSize 获取角色列表
     * @param pageNum 页面
     * @param pageSize 页面数量
     * @return pageInfo
     */
    @GetMapping("/list/page")
    public BaseResponse<Object> listRoleByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> list = permissionService.list();
        PageInfo<Permission> page = new PageInfo<Permission>(list);

        return ResultUtils.success(restPage(page));
    }

    /**
     * 获取所有权限信息
     * @return List<Permission>
     */
    @GetMapping("/listAll")
    public BaseResponse<List<Permission>> getListAll(){
        List<Permission> Permissions = permissionService.list();
        return ResultUtils.success(Permissions);
    }

    @PostMapping("/load")
    public BaseResponse<String> load(){
            permissionService.load();
        return ResultUtils.success("success");
    }




    /**
     * 删除角色 删除 角色-权限（已完成） 角色-menu（待添加）
     * @param id 角色id
     * @return success/error
     */
//    @DeleteMapping("/delete/{id}")
//    public BaseResponse<String> delete(@PathVariable Long id) {
//
//        String result = permissionService.deleteDep(id);
//
//        return ResultUtils.success(result);
//    }

    /**
     * 新增或更新角色 角色-权限（已完成） 角色-menu（待添加）
     * @param rolePermissionVO rolePermissionVO
     * @return success/error
     */
//    @PostMapping("/saveOrUpdate")
//    public BaseResponse<String> saveOrUpdate(@RequestBody RolePermissionVO rolePermissionVO){
//       String result = permissionService.saveOrUpdatePer(rolePermissionVO);
//        return ResultUtils.success(result);
//    }

    /**
     * 获取单个角色信息
     * @param id id
     * @return Permission
     */
//    @GetMapping("/info/{id}")
//    public BaseResponse<Permission> getInfo (@PathVariable Long id){
//        Permission result = permissionService.getInfo(id);
//        return ResultUtils.success(result);
//    }



//    @GetMapping("/query/{id}")
//    public BaseResponse<List<Permission>> query(@PathVariable Long id){
//        List<Permission> rs = permissionService.queryByUid(id);
//        return ResultUtils.success(rs);
//    }
}
