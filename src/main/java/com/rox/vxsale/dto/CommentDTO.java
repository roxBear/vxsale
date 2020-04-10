package com.rox.vxsale.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Data
public class CommentDTO {
    //评论表id
    @Id
    private Integer commentId;

    //评论者的名字
    private String commentName;

    //评论者的openid
    private String openId;

    //评论者头像
    private String avatarUrl;

    //评论内容
    private String commentContent;

    //创建时间
    private Date createTime;

    //具体订单TODO
    private String orderId;

    //评分
    private Integer commentCount;
}
