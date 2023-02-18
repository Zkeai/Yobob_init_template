package com.hupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hupi.project.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hupi.project.model.domain.User
 */
public interface UserMapper extends BaseMapper<User> {
    //更新管理员状态
    int updateStates(@Param("id") Long id,@Param("status") int status);
    //用户和角色关系表批量插入
    void insertBatchRelation(@Param("userId") Long userId,@Param("roleIds") Long[] roleIds);
    //用户和岗位关系批量插入
    void insertBatchPosts(@Param("userId") Long userId,@Param("postIds") Long[] postIds);

    String getPwdById(@Param("id") Long id);

    List<Integer> getRoleIds(@Param("id") Long id);

    int updateIsBanById(@Param("isBan") Long isBan,@Param("id") Long id);
}




