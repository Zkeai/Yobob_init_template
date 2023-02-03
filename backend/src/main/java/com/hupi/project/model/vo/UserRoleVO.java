package com.hupi.project.model.vo;

import com.hupi.project.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleVO {

    private User user;
    private Long[] roleIds;

}
