package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.ProductCategory;
import com.rox.vxsale.mapper.ProductCategoryMapper;
import com.rox.vxsale.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/1
 */
@Service
public class ProductCategoryServiceImp implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        ProductCategory category = categoryMapper.findById(categoryId);
        //业务处理
        return category;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> categories = categoryMapper.findAll();
        return categories;
    }

    @Override
    public List<ProductCategory> findByCategoryNumIn(List<Integer> categoryNumList) {
        List<ProductCategory> categoryList = categoryMapper.findByCategoryNumIn(categoryNumList);
        return categoryList;
    }

    @Override
    public int saveOne(ProductCategory productCategory) {
        int count = categoryMapper.saveOne(productCategory);
        return count;
    }

    @Override
    public List<ProductCategory> findByCategoryNum(Integer categoryNum) {
        List<ProductCategory> productByCategory = categoryMapper.findByCategoryNum(categoryNum);
        return productByCategory;
    }
}
