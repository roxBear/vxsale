package com.rox.vxsale.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@Data
public class ProductVo {

    private String categoryName;

    private Integer categoryNum;

    @JsonProperty("product")
    private List<ProductInfoVo> productInfoVoList;

}
