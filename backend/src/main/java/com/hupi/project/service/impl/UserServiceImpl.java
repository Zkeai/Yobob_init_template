package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.mapper.SysMenuMapper;
import com.hupi.project.mapper.SysRoleMapper;
import com.hupi.project.mapper.SysUserRoleMapper;
import com.hupi.project.mapper.UserMapper;
import com.hupi.project.model.entity.SysMenu;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.model.entity.SysUserRole;
import com.hupi.project.model.entity.User;
import com.hupi.project.model.vo.AdminVO;
import com.hupi.project.model.vo.UserRoleVO;
import com.hupi.project.service.UserService;
import com.hupi.project.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

import static com.hupi.project.constant.UserConstant.ADMIN_ROLE;
import static com.hupi.project.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户服务实现类
 *
 * @author yupi
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
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "%&*%hu-pi%*&%";

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
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
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

    @Override
    public String userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        //4.jwt生成
        String role = user.getUserRole();
        return JwtUtil.createToken(userAccount,role);
    }


    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }


    @Override
    public boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && ADMIN_ROLE.equals(user.getUserRole());
    }


    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
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
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUserAvatar(originUser.getUserAvatar());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setStatus(originUser.getStatus());
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
            //调用新增方法 把下级信息保存到user数据库
            userMapper.insert(user);
        }else{
            //编辑方法

            //插入用户 密码加密
            user.setUserPassword(DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes()));
            userMapper.updateById(user);

            //删除旧关系
            Map<String,Object> map=new HashMap<>();
            map.put("user_id",user.getId());
            sysUserRoleMapper.deleteByMap(map);
        }

        //插入新关系
        Long[] roleIds = userRoleVO.getRoleIds();
        if(roleIds !=null && roleIds.length > 0){
            //批量插入
            userMapper.insertBatchRelation(user.getId(),roleIds);
        }
        return "success";
    }

    @Override
    public String deleteEmp(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //删除员工信息
        int count = userMapper.deleteById(id);
        if(count == 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        //删除关系表数据
        Map<String,Object> map=new HashMap<>();
        map.put("user_id",id);
        sysUserRoleMapper.deleteByMap(map);

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
               String perms = sysMenu.getPerms();
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


}




