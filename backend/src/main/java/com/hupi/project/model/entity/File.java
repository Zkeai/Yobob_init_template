package com.hupi.project.model.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @TableName file
 */

@AllArgsConstructor
@ToString
@Data
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String md5;
    private String path;
    private Date upload_time;

    public File() {
    }

    public File(String name, String md5, String path, Date uploadTime) {
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.upload_time = uploadTime;
    }

}