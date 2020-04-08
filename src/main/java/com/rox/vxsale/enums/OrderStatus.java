package com.rox.vxsale.enums;

import lombok.Getter;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Getter
public enum  OrderStatus implements EnumCode{
    NEW(0,"新订单"),
    PAYED(1,"已支付"),
    CANCEL(2,"已取消"),
    FINISHED(3,"已完成"),
    COMMENT(4,"已评价"),
    ;

    private Integer code;

    private String message;


    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
