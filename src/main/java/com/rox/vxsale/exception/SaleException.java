package com.rox.vxsale.exception;

import lombok.Getter;

/**
 * @author roxBear
 * @creat 2020/4/5
 */
@Getter
public class SaleException extends RuntimeException {

    private Integer code;
    private String message;

    public SaleException(SaleErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public SaleException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
