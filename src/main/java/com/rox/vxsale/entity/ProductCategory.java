package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/3/31
 */
@Entity
@Data
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private Integer categoryNum;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryNum, Date createTime, Date updateTime) {
        this.categoryName = categoryName;
        this.categoryNum = categoryNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductCategory() {
    }


    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryNum=" + categoryNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
