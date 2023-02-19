package com.hupi.project.controller;


import com.github.pagehelper.PageInfo;
import com.hupi.project.common.BaseResponse;
import com.hupi.project.common.ResultUtils;

import com.hupi.project.model.dto.other.IsBanRequest;
import com.hupi.project.model.dto.post.PostListRequest;
import com.hupi.project.model.entity.Post;

import com.hupi.project.service.PostService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import static com.hupi.project.util.JsonPage.restPage;


@RestController
@RequestMapping("/post")
public class PostController{
    @Resource
    private PostService postService;


    @PostMapping("/list")
    public BaseResponse<Object> listRoleByPage(@RequestBody PostListRequest postListRequest) {
        PageInfo list = postService.getList(postListRequest);

        return ResultUtils.success(restPage(list));
    }

    @GetMapping("/listAll")
    public BaseResponse<List<Post>> getAll() {
        List<Post> postList = postService.list();

        return ResultUtils.success(postList);
    }

    @PostMapping("/saveOrUpdate")
    public BaseResponse<String> saveOrUpdate(@RequestBody Post post){
        String result = postService.saveOrUpdateIs(post);
        return ResultUtils.success(result);
    }

    @PostMapping("/updateIsBan")
    public BaseResponse<Boolean> updateIsBan(@RequestBody IsBanRequest isBanRequest){
        Long isBan = isBanRequest.getIsBan();
        Long id = isBanRequest.getId();
        Boolean res = postService.updateIsBan(isBan,id);
        return ResultUtils.success(res);
    }

    /**
     * 删除岗位
     * @param id 岗位id
     * @return success/error
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {

        String result = postService.deleteDep(id);

        return ResultUtils.success(result);
    }
}