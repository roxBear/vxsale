package com.rox.vxsale.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Entity
@Table(name="order_detail")
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;
}
