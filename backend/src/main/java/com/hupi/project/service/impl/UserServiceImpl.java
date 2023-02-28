package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.*;
import com.hupi.project.model.dto.user.UserQueryRequest;
import com.hupi.project.model.entity.*;
import com.hupi.project.model.vo.AdminVO;
import com.hupi.project.model.vo.UserRoleVO;
import com.hupi.project.model.vo.UserVO;
import com.hupi.project.service.DepartmentService;
import com.hupi.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import static com.hupi.project.controller.UserController.assembleUserListVo;
import static com.hupi.project.util.StringUtils.transferString2Date;

/**
 * 用户服务实现类
 * @author zkeai
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysUserPostMapper sysUserPostMapper;
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = userMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 2. 加密
            String encryptPassword = bCryptPasswordEncoder.encode(userPassword);

            // 3. 插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }


    /**
     * 用户脱敏
     * @param originUser originUser
     * @return User
     */
    @Override
    public User getSafetyUser(User originUser){
        if(originUser == null){
            return null;
        }

        User safetyUser =new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setDeptId(originUser.getDeptId());
        safetyUser.setStatus(originUser.getStatus());
        safetyUser.setIsBan(originUser.getIsBan());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUserAvatar(originUser.getUserAvatar());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setAge(originUser.getAge());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setUpdateTime(originUser.getUpdateTime());

        return safetyUser;
    }

    @Override
    public String saveOrUpdate(UserRoleVO userRoleVO) {
        if(userRoleVO ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(userRoleVO.getUser() ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        User user = userRoleVO.getUser();

        if(userRoleVO.getUser().getId() == null){
            user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
            if(userRoleVO.getDeptIds().length > 1){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
            } else if (userRoleVO.getDeptIds().length == 1) {
                user.setDeptId(userRoleVO.getDeptIds()[0]);
            }

            //新增用户
            userMapper.insert(user);
        }else{

            //判断是否
            if(userRoleVO.getDeptIds().length > 1){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
            } else if (userRoleVO.getDeptIds().length == 1) {
                user.setDeptId(userRoleVO.getDeptIds()[0]);
            }


            //判断密码是否为空
            if(Objects.equals(user.getUserPassword(), "") || user.getUserPassword()== null){
               String pwd = userMapper.getPwdById(user.getId());
               user.setUserPassword(pwd);
            }
            //更新用户
            userMapper.updateById(user);

            Map<String,Object> map=new HashMap<>();
            map.put("user_id",user.getId());
            if(userRoleVO.getRoleIds().length > 0){
                //删除旧关系 用户-角色
                sysUserRoleMapper.deleteByMap(map);
            }
            if(userRoleVO.getPostIds().length > 0){
                //删除旧关系 用户-岗位
                sysUserPostMapper.deleteByMap(map);
            }

        }

        //插入新关系
        Long[] roleIds = userRoleVO.getRoleIds();

        Long[] postIds = userRoleVO.getPostIds();
        if( roleIds.length > 0 ){
            //批量插入用户-角色
            userMapper.insertBatchRelation(user.getId(),roleIds);
        }
        if( postIds.length > 0){
            //批量插入用户-岗位
            userMapper.insertBatchPosts(user.getId(),postIds);
        }
        return "success";
    }

    @Override
    public String deleteEmp(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //删除员工信息
        boolean count = userService.removeById(id);
        if(!count){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        //删除关系表数据 用户-角色 用户-岗位
        Map<String,Object> map=new HashMap<>();
        map.put("user_id",id);
        sysUserRoleMapper.deleteByMap(map);
        sysUserPostMapper.deleteByMap(map);
        return "success";
    }

    /**
     *获取用户所有信息
     * @param UserId UserId
     * @return String
     */
    @Override
    public String getUserAuthorityInfo(Long UserId) {
       StringBuilder authority = new StringBuilder();
        //根据用户id获取所有的角色信息
        List<SysRole> roleList  = sysRoleMapper.selectList(new QueryWrapper<SysRole>().inSql("id","SELECT role_id FROM sys_user_role WHERE user_id="+UserId));
        if(roleList.size()>0){
          String roleCodeStr =  roleList.stream().map(r->"ROLE_"+r.getCode()).collect(Collectors.joining(","));
          authority.append(roleCodeStr);
        }
        //遍历所有的角色,获取所有的菜单权限 且 不重复
        Set<String> menuCodeSet = new HashSet<>();
        for(SysRole sysRole:roleList){
            List<SysMenu> sysMenuList =  sysMenuMapper.selectList(new QueryWrapper<SysMenu>().inSql("id","SELECT menu_id FROM sys_role_menu WHERE role_id="+sysRole.getId()));
            for(SysMenu sysMenu:sysMenuList){
               String perms = sysMenu.getCode();
               if(StringUtils.isNotBlank(perms)){
                   menuCodeSet.add(perms);
               }
            }
        }
        if(menuCodeSet.size()>0){
            authority.append(",");
            String menuCodeStr =  menuCodeSet.stream().collect(Collectors.joining(","));
            authority.append(menuCodeStr);
        }
       return authority.toString();
    }

    @Override
    public String updateState(AdminVO adminVO) {
        if(adminVO == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        int count = userMapper.updateStates(adminVO.getId(),adminVO.getStatus());
        if(count == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"更新失败");
        }
        return "success";
    }

    @Override
    public User getByAccount(String userAccount) {

        return getOne(new QueryWrapper<User>().eq("userAccount",userAccount));
    }

    @Override
    public PageInfo getList(UserQueryRequest userQueryRequest) {
        //根据前端上传的条件筛选
        User userQuery = new User();
        if (StringUtils.isNotBlank(userQueryRequest.getUserName())) {
            userQuery.setUserName(userQueryRequest.getUserName());
        }
        if(StringUtils.isNotBlank(userQueryRequest.getPhone())) {
            userQuery.setPhone(userQueryRequest.getPhone());
        }
        if(!Objects.equals(userQueryRequest.getEmail(), "") && userQueryRequest.getEmail() !=null) {
            userQuery.setEmail(userQueryRequest.getEmail());
        }
        if(userQueryRequest.getIsBan() !=null){
            if(userQueryRequest.getIsBan() != 1000){
                userQuery.setIsBan(userQueryRequest.getIsBan());
            }

        }
        if( userQueryRequest.getGender() !=null){
            if(userQueryRequest.getGender() != 1000){
                userQuery.setGender(userQueryRequest.getGender());
            }

        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        if(!Objects.equals(userQueryRequest.getCreateTime(), "") && userQueryRequest.getCreateTime() !=null){
            String time= userQueryRequest.getCreateTime();
            Date a = transferString2Date(time.split("`")[0]);
            Date b = transferString2Date(time.split("`")[1]);
            queryWrapper.ge("createTime",a);
            queryWrapper.le("createTime",b);
        }
        if(!Objects.equals(userQueryRequest.getUpdateTime(), "") && userQueryRequest.getUpdateTime() !=null){
            String time= userQueryRequest.getUpdateTime();
            Date a = transferString2Date(time.split("`")[0]);
            Date b = transferString2Date(time.split("`")[1]);
            queryWrapper.ge("updateTime",a);
            queryWrapper.le("updateTime",b);
        }

        //根据deptId获取所有下面的部门
        Long deptId = userQueryRequest.getDeptId();
        QueryWrapper<Department> deptQueryWrapper = new QueryWrapper<>();
        deptQueryWrapper.like("ancestors",deptId);
        List<Department> deptList = departmentService.list(deptQueryWrapper);
        List<Long> deptArray = deptList.stream().map(Department::getId).collect(Collectors.toList());
        if (deptArray.size() > 0) {
            List<String> stringList = new ArrayList<>();
            deptArray.stream().forEach(o -> {
                stringList.add(String.valueOf(o));
            });
            queryWrapper.in("deptId",stringList);
        } else if (deptId != null)
        {
            queryWrapper.eq("deptId",deptId);
        }
        PageHelper.startPage(userQueryRequest.getPageNum(),userQueryRequest.getPageSize());
        List<User> userList = userService.list(queryWrapper);
        PageInfo<Object> page = new PageInfo<>(userList);
        List<Object> voList = new ArrayList<>();

        for(User item:userList){
            UserRoleVO userRoleVO = getUserInfo(item,item.getId());
            UserVO userVO =assembleUserListVo(userRoleVO);
            voList.add(userVO);
        }
        page.setList(voList);

        return page;
    }

    @Override
    public Boolean updateIsBan(Long isBan,Long id) {

        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        if(isBan !=0 && isBan !=1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        int res = userMapper.updateIsBanById(isBan,id);
        if(res>0){
            return true;
        }
        return false;
    }

    public UserRoleVO getUserInfo(User user,Long UserId) {


        //根据用户id获取所有的角色信息
        List<SysUserRole> roleList  = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().inSql("role_id","SELECT role_id FROM sys_user_role WHERE user_id="+UserId));
        Set<Long> roleSet = new HashSet<>();

        for(SysUserRole sysUserRole:roleList){
            Long role_id = sysUserRole.getRole_id();
            if(role_id > 0){
                roleSet.add(role_id);
            }
        }

        //遍历用户对应的岗位
        List<SysUserPost> postList =  sysUserPostMapper.selectList(new QueryWrapper<SysUserPost>().inSql("post_id","SELECT post_id FROM sys_user_post WHERE user_id="+UserId));
        Set<Long> postSet = new HashSet<>();
        for(SysUserPost sysUserPost:postList){
            Long post_id = sysUserPost.getPost_id();
            if(post_id > 0){
                postSet.add(post_id);
            }
        }

        // 拼接到一个对象中
            UserRoleVO userRoleVO = new UserRoleVO();
            userRoleVO.setUser(user);
            userRoleVO.setRoleIds(roleSet.toArray(new Long[roleSet.size()]));
            userRoleVO.setPostIds(postSet.toArray(new Long[postSet.size()]));

        return userRoleVO;
    }
}




