package com.rox.vxsale.dto;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Data
public class ProductInfoDTO {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private String productIcon;

}
