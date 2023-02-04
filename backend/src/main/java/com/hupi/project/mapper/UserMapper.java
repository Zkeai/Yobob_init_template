package com.hupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hupi.project.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.hupi.project.model.domain.User
 */
public interface UserMapper extends BaseMapper<User> {
    int updateStates(@Param("id") Long id,@Param("status") int status);

    void insertBatchRelation(@Param("userId") Long userId,@Param("roleIds") Long[] roleIds);
}




