package com.hupi.project.service;

import com.hupi.project.model.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author saoren
* @description 针对表【file】的数据库操作Service
* @createDate 2023-03-06 18:04:13
*/
public interface FileService extends IService<File> {

    boolean checkMd5(String md5);

    void uploadWithBlock(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException;

    void upload(String name, String md5, MultipartFile file) throws IOException;
}
