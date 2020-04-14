package com.rox.vxsale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rox.vxsale.entity.OrderDetail;
import com.rox.vxsale.enums.EnumUtil;
import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.enums.PayStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Data
public class OrderDTO {

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
    /*每个订单中多个详情,一对多*/
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatus getOrderStatus() {
        return EnumUtil.getByCode(orderStatus, OrderStatus.class);
    }

    @JsonIgnore
    public PayStatus getPayStatus() {
        return EnumUtil.getByCode(payStatus, PayStatus.class);
    }

}
