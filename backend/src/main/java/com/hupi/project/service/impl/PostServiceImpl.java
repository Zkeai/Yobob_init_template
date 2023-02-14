package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.model.entity.Post;
import com.hupi.project.service.PostService;
import com.hupi.project.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author saoren
* @description 针对表【post】的数据库操作Service实现
* @createDate 2023-02-13 21:23:03
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




