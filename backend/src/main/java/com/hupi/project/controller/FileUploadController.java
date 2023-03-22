package com.hupi.project.controller;

import com.hupi.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     */
    @GetMapping("/")
    public boolean upload(String md5) {
        return fileService.checkMd5(md5);
    }

    /**
     * 大文件上传
     */
    @PostMapping("/BigFile")
    public void upload(String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            fileService.upload(name, md5,file);
        }
    }

    /**
     * 秒传
     */
    @GetMapping("/QuickUpload")
    public boolean QuickUpload(String md5) {
        return fileService.checkMd5(md5);
    }

}
