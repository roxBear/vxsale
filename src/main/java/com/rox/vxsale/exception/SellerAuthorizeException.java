package com.rox.vxsale.exception;

import lombok.Getter;

/**
 * @author roxBear
 * @creat 2020/4/12
 */
@Getter
public class SellerAuthorizeException extends RuntimeException {

    private String msg;
    public SellerAuthorizeException(String msg) {
        this.msg = msg;
    }
}
