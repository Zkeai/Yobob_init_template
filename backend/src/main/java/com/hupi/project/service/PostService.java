package com.hupi.project.service;

import com.github.pagehelper.PageInfo;
import com.hupi.project.model.dto.post.PostListRequest;
import com.hupi.project.model.dto.post.PostSaveOrUpdateRequest;
import com.hupi.project.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author saoren
* @description 针对表【post】的数据库操作Service
* @createDate 2023-02-13 21:23:03
*/
public interface PostService extends IService<Post> {

    Boolean updateIsBan(Long isBan, Long id);

    PageInfo getList(PostListRequest postListRequest);

    String deleteDep(Long id);

    String saveOrUpdateIs(Post post);

}
