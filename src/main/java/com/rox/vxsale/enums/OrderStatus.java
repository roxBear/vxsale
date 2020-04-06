package com.rox.vxsale.enums;

import lombok.Getter;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Getter
public enum  OrderStatus {
    NEW(0,"新订单"),
    FINISHED(1,"已完成"),
    CANCEL(3,"已取消"),
    ;

    private Integer code;

    private String message;


    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
