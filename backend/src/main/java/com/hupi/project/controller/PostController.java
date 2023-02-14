package com.hupi.project.controller;


import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;

import com.hupi.project.model.entity.Post;


import com.hupi.project.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController{
    @Resource
    private PostService postService;

    @GetMapping("/listAll")
    public BaseResponse<List<Post>> getAll() {
        List<Post> postList = postService.list();

        return ResultUtils.success(postList);
    }
}