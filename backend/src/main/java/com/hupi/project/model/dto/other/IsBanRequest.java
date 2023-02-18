package com.hupi.project.model.dto.other;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author zkeai
 */
@Data
public class IsBanRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private Long isBan;

    private Long id;

}
