package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/2
 */
@Entity
@Data
@Table(name = "product_info")
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryNum;

    private Date createTime;

    private Date updateTime;
}
