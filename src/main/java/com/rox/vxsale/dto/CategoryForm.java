package com.rox.vxsale.dto;

import lombok.Data;

/**
 * @author roxBear
 * @creat 2020/4/8
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryNum;
}
