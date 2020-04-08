package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Data
@Entity
public class SellerInfo {

    //商家用户id
    @Id
    private Integer sellerId;

    //商家用户名称
    private String username;

    //商家登录密码
    private String password;

    //商家手机
    private String phone;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
