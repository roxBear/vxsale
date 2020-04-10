package com.rox.vxsale.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Data
public class UserForm {
    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String userName;

    //买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家openid
    @NotEmpty(message = "openid必填")
    private String openId;

    private String userTable;

    private String userNumber;
}
