package com.rox.vxsale.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author roxBear
 * @creat 2020/4/8
 */
@Data
public class ProductForm {

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品图片
    private String productIcon;

    //商品描述
    private String productDescription;

    //类目编号
    private Integer categoryNum;
}
