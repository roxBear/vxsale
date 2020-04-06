package com.rox.vxsale.entity;

import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.enums.PayStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Entity
@Data
@Table(name="order")
public class Order {
    @Id
    private String orderId;

    private String userName;

    private String userPhone;

    private String userAddress;

    private String userOpenid;
    /*总金额*/
    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatus.NEW.getCode();

    private Integer payStatus = PayStatus.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
