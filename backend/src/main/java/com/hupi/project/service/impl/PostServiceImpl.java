package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hupi.project.common.ErrorCode;
import com.hupi.project.exception.BusinessException;
import com.hupi.project.model.dto.post.PostListRequest;
import com.hupi.project.model.dto.post.PostSaveOrUpdateRequest;
import com.hupi.project.model.entity.Post;
import com.hupi.project.model.entity.SysRole;
import com.hupi.project.service.PostService;
import com.hupi.project.mapper.PostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hupi.project.util.StringUtils.transferString2Date;

/**
* @author saoren
* @description 针对表【post】的数据库操作Service实现
* @createDate 2023-02-13 21:23:03
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Resource
    private PostMapper postMapper;
    @Resource
    private PostService postService;
    @Override
    public Boolean updateIsBan(Long isBan, Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(isBan !=0 && isBan !=1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        int res = postMapper.updateIsBanById(isBan,id);
        if(res>0){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo getList(PostListRequest postListRequest) {
        PageHelper.startPage(postListRequest.getPageNum(),postListRequest.getPageSize());

        String name = postListRequest.getName();
        Integer isBan = postListRequest.getIsBan();
        String createTime =postListRequest.getCreateTime();
        String updateTime =postListRequest.getUpdateTime();
        String code = postListRequest.getCode();
        Post postQuery = new Post();
        if ( name!=null && !Objects.equals(name, "")) {
            postQuery.setName(postQuery.getName());
        }
        if(code!=null && !Objects.equals(code, "")){
            postQuery.setCode(postQuery.getCode());
        }
        if( isBan !=null){
            if(isBan != 1000){
                postQuery.setIsBan(isBan);
            }
        }

        QueryWrapper<Post> queryWrapper = new QueryWrapper<>(postQuery);
        if(!Objects.equals(createTime, "") && createTime != null){
            Date a = transferString2Date(createTime.split("`")[0]);
            Date b = transferString2Date(createTime.split("`")[1]);
            queryWrapper.ge("createTime",a);
            queryWrapper.le("createTime",b);
        }
        if(!Objects.equals(updateTime, "") && updateTime != null){
            Date a = transferString2Date(updateTime.split("`")[0]);
            Date b = transferString2Date(updateTime.split("`")[1]);
            queryWrapper.ge("updateTime",a);
            queryWrapper.le("updateTime",b);
        }


        List<Post> list = postService.list();
        PageInfo<Post> page = new PageInfo<Post>(list);
        page.setList(list);
        return page;
    }

    @Override
    public String deleteDep(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int count = postMapper.deleteById(id);
        if(count == 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return "success";
    }

    @Override
    public String saveOrUpdateIs(Post post) {
        if(post ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }
        if(post.getId()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"非法操作");
        }

        if( post.getId() == 0){
            //调用新增方法 把下级信息保存到role数据库
            postMapper.insert(post);
        }else{
            //编辑方法
            postMapper.updateById(post);
        }

        return null;
    }
}




