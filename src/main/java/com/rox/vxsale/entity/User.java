package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Entity
@Data
public class User {
    //用户id
    @Id
    @GeneratedValue
    private Integer userId;

    //用户名称
    private String userName;

    //用户手机号
    private String userPhone;

    //用户openid
    private String openId;

    //用户就餐的桌号
    private String userTable;

    //用户就餐人数
    private String userNumber;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
