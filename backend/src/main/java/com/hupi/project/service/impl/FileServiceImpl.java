package com.hupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hupi.project.config.UploadConfig;
import com.hupi.project.model.entity.File;
import com.hupi.project.service.FileService;
import com.hupi.project.mapper.FileMapper;
import com.hupi.project.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

import static com.hupi.project.util.FileUtils.generateFileName;
import static com.hupi.project.util.UploadUtils.*;

/**
* @author saoren
* @description 针对表【file】的数据库操作Service实现
* @createDate 2023-03-06 18:04:13
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService{

    @Resource
    private FileMapper fileMapper;
    @Override
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileMapper.getByFile(file) == null;
    }

    @Override
    public void uploadWithBlock(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            fileMapper.save(new File(name, md5,UploadConfig.path + fileName, new Date()));
        }
    }

    @Override
    public void upload(String name, String md5, MultipartFile file) throws IOException {
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        fileMapper.save(new File(name, md5, path, new Date()));
    }
}




