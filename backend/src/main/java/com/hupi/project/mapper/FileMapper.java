package com.hupi.project.mapper;

import com.hupi.project.model.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author saoren
* @description 针对表【file】的数据库操作Mapper
* @createDate 2023-03-06 17:30:52
* @Entity com.hupi.project.model.entity.File
*/
public interface FileMapper extends BaseMapper<File> {
    /**
     * 通过主键获取一行数据
     * @return
     */
    File getById(Long id);

    /**
     * 插入一行数据
     * @param file
     * @return
     */
    int save(File file);

    /**
     * 更新一行数据
     * @param file
     * @return
     */
    int update(File file);

    /**
     * 删除一行数据
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据一个或多个属性获取File
     * @param file
     * @return
     */
    File getByFile(File file);
}




