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
@Data
@Entity
public class Picture {
    //图片id
    @Id
    private Integer picId;

    //图片地址
    private String picUrl;

    //图片信息
    private String picMessage;

    //创建时间
    private Date createTime;
}
