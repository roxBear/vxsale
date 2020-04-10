package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Data
@Entity
public class Comment {

    //评论表id
    @Id
    private Integer commentId;

    //评论者的名字
    private String commentName;

    //评论者的openid
    private String openId;

    //评论内容
    private String commentContent;

    //创建时间
    private Date createTime;
}
