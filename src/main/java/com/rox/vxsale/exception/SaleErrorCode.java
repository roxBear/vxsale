package com.rox.vxsale.exception;

/**
 * @author roxBear
 * @creat 2020/4/5
 */
public enum SaleErrorCode implements ErrorCode {
    SUCCESS(2000, "成功"),

    PARAM_ERROR(3001, "参数不正确"),

    PRODUCT_NOT_EXIST(4001, "商品不存在"),

    PRODUCT_STOCK_ERROR(3002, "商品库存不正确"),

    ORDER_NOT_EXIST(4002, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(4003, "订单详情不存在"),

    ORDER_STATUS_ERROR(3003, "订单状态不正确"),

    ORDER_UPDATE_FAIL(5001, "订单更新失败"),

    ORDER_DETAIL_EMPTY(4004, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(5002, "订单支付状态不正确"),

    CART_EMPTY(4005, "购物车为空"),

    ORDER_OWNER_ERROR(5003, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(5004, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(5005, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(2002, "订单取消成功"),

    ORDER_FINISH_SUCCESS(2003, "订单完结成功"),

    PRODUCT_STATUS_ERROR(3003, "商品状态不正确"),

    LOGIN_FAIL(3004, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(2004, "登出成功"),

    USER_NOT_FOUND(4006,"用户不存在"),
    ;

    private Integer code;
    private String message;


    SaleErrorCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
