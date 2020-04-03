package com.rox.vxsale.service;

import com.rox.vxsale.entity.ProductCategory;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/1
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryNumIn(List<Integer> categoryNumList);

    int saveOne(ProductCategory productCategory);

    List<ProductCategory> findByCategoryNum(Integer categoryNum);
}
