package com.hupi.project.mapper;

import com.hupi.project.model.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author saoren
* @description 针对表【post】的数据库操作Mapper
* @createDate 2023-02-13 21:23:02
* @Entity com.hupi.project.model.entity.Post
*/
public interface PostMapper extends BaseMapper<Post> {

    int updateIsBanById(@Param("isBan") Long isBan,@Param("id") Long id);
}




