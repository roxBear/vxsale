package com.rox.vxsale.dto;

import lombok.Data;

/**
 * 购物车
 * @author roxBear
 * @creat 2020/4/5
 */
@Data
public class CarDTO {

    private String productId;
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
